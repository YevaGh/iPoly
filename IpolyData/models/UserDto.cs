using IpolyData.entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IpolyData.models
{
    public class UserDto
    {
        public string Role {  get; set; }
        public string Name { get; set; }
        public string Lastname { get; set; }
        public string Sex { get; set; }
        public string Email { get; set; }
        public string BloodGroup { get; set; }
        public string Mobile { get; set; }
        public DateTime BirthDate { get; set; }
        
        public string Password {  get; set; }
    }
}
