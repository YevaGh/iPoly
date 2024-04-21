using IpolyData.entities;
using IpolyData.models;
using IpolyData.services;
using Microsoft.AspNetCore.Mvc;

namespace Ipoly.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UsersController : Controller
    {
        private readonly IUserService _userService;

        public UsersController(IUserService userService)
        {
            _userService = userService;
        }

        [HttpPost("add_user")]
        public async Task<long> AddUser([FromBody] UserDto userDto)
        {
            return await _userService.AddUserAsync(userDto);
        }

        [HttpGet("get_user_data")]
        public async Task<UserInfo> GetUserInfo(long user_id)
        {
            return await _userService.GetUserInfoAsync(user_id);
        }

        [HttpGet("get_absence_gpa")]
        public async Task<AbsenceANDGpa> GetUserAbsence(long user_id)
        {
            return await _userService.GetAbsenceAndGpaAsync(user_id);
        }

        [HttpGet("authenticate")]
        public async Task<string> UserAuthenticate(string email, string password)
        {
            return await _userService.AutheticateUserAsync(email, password);
        }

        [HttpPut("update_absence_count")]
        public async Task<bool> UpdateAbsenceCount(long user_id, int count)
        {
            return await _userService.UpdateAbsenceAsync(user_id, count);
        }

        [HttpPut("update_gpa")]
        public async Task<bool> UpdateGpa(long user_id, double gpa)
        {
            return await _userService.UpdateGpaAsync(user_id, gpa);
        }

        [HttpGet("get_user_id_by_session")]
        public async Task<long> UserIdBySession(string session_id)
        {
            return await _userService.GetUserIdBySessionAsync(session_id);
        }

    }
}
