using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace IpolyData.entities
{
    [Table("absences_gpa")]
    public class AbsenceANDGpa
    {
        [Key]
        [Column("id")]
        public long AbsenceID { get; set; }

        [ForeignKey("User")]
        [Column("student_id")]
        public long StudentID { get; set; }

        [Column("absence_count")]
        public int AbsenceCount { get; set; }
        [Column("gpa")]
        public double Gpa { get; set; }
        public User User { get; set; }
    }
}
