using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace IpolyData.entities
{
    [Table("users")]
    public class User
    {
        [Key]
        [Column("user_id")]
        public long UserId { get; set; }
        [Column("role")]
        public string Role { get; set; }
        [Column("email")]
        public string Email { get; set; }
        [Column("password")]
        public string Password { get; set; }

        public UserInfo UserInfo { get; set; }

        public AbsenceANDGpa AbsenceGpa { get; set; }
        public List<Session> Sessions { get; set; }
    }
}
