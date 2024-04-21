using IpolyData.entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;

namespace IpolyData
{
    public class UsersContext : DbContext
    {
        public DbSet<User> Users { get; set; }
        public DbSet<UserInfo> UserInfos { get; set; }
        public DbSet<AbsenceANDGpa> Absences { get; set; }
        public DbSet<Session> Sessions { get; set; }

        protected readonly IConfiguration Configuration;

        public UsersContext(IConfiguration configuration)
        {
            Configuration = configuration;
        }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseNpgsql(Configuration.GetConnectionString("polytechDB"));
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>()
                .HasOne(u => u.UserInfo)
                .WithOne(i => i.User)
                .HasForeignKey<UserInfo>(i => i.StudentId)
                .OnDelete(DeleteBehavior.Cascade); 

            modelBuilder.Entity<User>()
                .HasOne(u => u.AbsenceGpa)
                .WithOne(a => a.User)
                .HasForeignKey<AbsenceANDGpa>(a => a.StudentID)
                .OnDelete(DeleteBehavior.Cascade);

            modelBuilder.Entity<User>()
                .HasMany(u => u.Sessions) 
                .WithOne(s => s.User)    
                .HasForeignKey(s => s.UserID) 
                .OnDelete(DeleteBehavior.Cascade);
        }
    }
}
