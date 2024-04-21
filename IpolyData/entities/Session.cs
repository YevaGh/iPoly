using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace IpolyData.entities
{
    [Table("session")]
    public class Session
    {
        [Key]
        [Column("id")]
        public long SessionID { get; set; }

        [ForeignKey("User")]
        [Column("user_id")]
        public long UserID { get; set; }

        [Column("session_id")]
        public string SessionHash { get; set; }
        public User User { get; set; }
    }
}
