using IpolyData.entities;
using Microsoft.EntityFrameworkCore;

namespace IpolyData.repositories
{
    public interface IUserRepository
    {
        Task<long> AddUserAsync(User user);
        Task<bool> UpdateAbsenceAsync(long studentId, int absenceCount);
        Task<bool> UpdateGpaAsync(long studentId, double gpa);
        Task<UserInfo> GetUserInfoAsync(long studentId);
        Task<AbsenceANDGpa> GetAbsenceAndGpaAsync(long studentId);
        Task<string> AutheticateUserAsync(string email, string hashedPassword, string hashedSessionId);
        Task<long> GetUserIdBySessionAsync(string sessionId);
    }
    public class UserRepository : IUserRepository
    {
        private readonly UsersContext _dbContext;

        private readonly DbSet<User> _dbSetUsers;
        private readonly DbSet<UserInfo> _dbSetUsersInfo;
        private readonly DbSet<AbsenceANDGpa> _dbSetUsersAbsenceGpa;
        private readonly DbSet<Session> _dbSetSessions;

        public UserRepository(UsersContext dbContext)
        {
            _dbContext = dbContext;
            _dbSetUsers = dbContext.Users;
            _dbSetUsersInfo = dbContext.UserInfos;
            _dbSetUsersAbsenceGpa = dbContext.Absences;
            _dbSetSessions = dbContext.Sessions;
        }

        public async Task<long> AddUserAsync(User user)
        {
            _dbSetUsers.Add(user);
            await _dbContext.SaveChangesAsync();
            return user.UserId;
        }

        public async Task<string> AutheticateUserAsync(string email, string hashedPassword, string hashedSessionId)
        {
            var user = await _dbSetUsers.FirstOrDefaultAsync(x => x.Email == email && x.Password == hashedPassword);
            if (user == null)
            {
                return "0";
            }
            var session = new Session() { SessionHash = hashedSessionId };

            if (user.Sessions == null)
            {
                user.Sessions = new List<Session> { session };
            }
            else
            {
                user.Sessions.Add(session);
            }
            await _dbContext.SaveChangesAsync();

            return hashedSessionId;
        }

        public async Task<AbsenceANDGpa> GetAbsenceAndGpaAsync(long studentId)
        {
            return await _dbSetUsersAbsenceGpa.FirstOrDefaultAsync(x => x.StudentID == studentId);
        }

        public async Task<long> GetUserIdBySessionAsync(string sessionId)
        {
            var session = await _dbSetSessions.FirstOrDefaultAsync(x => x.SessionHash == sessionId);
            return session != null ? session.UserID : 0;
        }

        public async Task<UserInfo> GetUserInfoAsync(long studentId)
        {
            return await _dbSetUsersInfo.FirstOrDefaultAsync(x => x.StudentId == studentId);
        }

        public async Task<bool> UpdateAbsenceAsync(long studentId, int absenceCount)
        {
            try
            {
                var user = await _dbSetUsers.FindAsync(studentId);
                if (user == null) throw new Exception();

                var absenceGpa = await _dbSetUsersAbsenceGpa.FirstOrDefaultAsync(x => x.StudentID == studentId);
                if (absenceGpa != null)
                {
                    absenceGpa.AbsenceCount = absenceCount;
                    _dbSetUsersAbsenceGpa.Update(absenceGpa);
                }

                await _dbContext.SaveChangesAsync();

                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        public async Task<bool> UpdateGpaAsync(long studentId, double gpa)
        {
            try
            {
                var user = await _dbSetUsers.FindAsync(studentId);
                if (user == null) throw new Exception();

                var absenceGpa = await _dbSetUsersAbsenceGpa.FirstOrDefaultAsync(x => x.StudentID == studentId);
                if (absenceGpa != null)
                {
                    absenceGpa.Gpa = gpa;
                    _dbSetUsersAbsenceGpa.Update(absenceGpa);
                }

                await _dbContext.SaveChangesAsync();

                return true;
            }
            catch (Exception ex)
            {
                return false;
            }
        }
    }
}
