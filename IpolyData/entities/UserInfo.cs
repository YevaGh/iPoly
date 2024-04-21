using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace IpolyData.entities
{
    [Table("personal_data")]
    public class UserInfo
    {
        [Key]
        [Column("id")]
        public long InfoId { get; set; }

        [ForeignKey("User")]
        [Column("student_id")]
        public long StudentId { get; set; }
        [Column("name")]
        public string Name { get; set; }
        [Column("lastname")]
        public string Lastname { get; set; }
        [Column("sex")]
        public string Sex { get; set; }
        [Column("blood_group")]
        public string BloodGroup { get; set; }
        [Column("mobile")]
        public string Mobile { get; set; }
        [Column("birth_date")]
        public DateTime BirthDate { get; set; }

        public User User { get; set; }
    }
}
