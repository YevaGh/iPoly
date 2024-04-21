using IpolyData.entities;
using IpolyData.models;
using IpolyData.repositories;
using System.Security.Cryptography;
using System.Text;

namespace IpolyData.services
{
    public interface IUserService
    {
        Task<long> AddUserAsync(UserDto user);
        Task<bool> UpdateAbsenceAsync(long userId, int absenceCount);
        Task<bool> UpdateGpaAsync(long userId, double gpa);
        Task<UserInfo> GetUserInfoAsync(long studentId);
        Task<AbsenceANDGpa> GetAbsenceAndGpaAsync(long studentId);
        Task<string> AutheticateUserAsync(string email, string password);
        Task<long> GetUserIdBySessionAsync(string sessionId);

    }
    public class UserService : IUserService
    {
        private readonly IUserRepository _userRepository;

        public UserService(IUserRepository userRepository)
        {
            _userRepository = userRepository;
        }
        public async Task<long> AddUserAsync(UserDto userDto)
        {
            var info = new UserInfo()
            {
                Name = userDto.Name,
                Lastname = userDto.Lastname,
                Sex = userDto.Sex,
                BloodGroup = userDto.BloodGroup,
                Mobile = userDto.Mobile,
                BirthDate = userDto.BirthDate,
            };

            var absence = new AbsenceANDGpa();
            var sessions = new List<Session>() { };
            var user = new User { Role = userDto.Role, Email = userDto.Email, Password = HashPassword(userDto.Password), UserInfo = info, AbsenceGpa = absence,Sessions=sessions };

            return await _userRepository.AddUserAsync(user);

        }

        public async Task<string> AutheticateUserAsync(string email, string password)
        {
            string hashedPassword = HashPassword(password);
            var hashedSessionId = HashSession(email);

            return await _userRepository.AutheticateUserAsync(email, hashedPassword, hashedSessionId);
        }

        public async Task<AbsenceANDGpa> GetAbsenceAndGpaAsync(long studentId)
        {
            var absence = await _userRepository.GetAbsenceAndGpaAsync(studentId);
            return absence;

        }

        public Task<long> GetUserIdBySessionAsync(string sessionId)
        {
            return _userRepository.GetUserIdBySessionAsync(sessionId);
        }

        public Task<UserInfo> GetUserInfoAsync(long studentId)
        {
            return _userRepository.GetUserInfoAsync(studentId);
        }

        public async Task<bool> UpdateAbsenceAsync(long userId, int absenceCount)
        {
           return await _userRepository.UpdateAbsenceAsync(userId, absenceCount);
        }

        public async Task<bool> UpdateGpaAsync(long userId, double gpa)
        {
            return await _userRepository.UpdateGpaAsync(userId, gpa);
        }

        private string HashPassword(string password)
        {
            string FixedSalt = "$2a$10$D9lKukBij.FUilWc8B1u9u";
            return BCrypt.Net.BCrypt.HashPassword(password, FixedSalt);
        }

        private string HashSession(string email)
        {
            string sessionId = $"{email}_{DateTime.UtcNow.Ticks}";
            using (SHA256 sha256 = SHA256.Create())
            {
                byte[] bytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(sessionId));

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < bytes.Length; i++)
                {
                    builder.Append(bytes[i].ToString("x2"));
                }
                return builder.ToString();
            }
        }
    }
}
