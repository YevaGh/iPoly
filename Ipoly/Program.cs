using IpolyData;
using IpolyData.repositories;
using IpolyData.services;
using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(c =>
{
    c.SwaggerDoc("v1", new OpenApiInfo { Title = "Polytech", Version = "v1" });
});

var builderConfig = new ConfigurationBuilder()
    .SetBasePath(Directory.GetCurrentDirectory())
    .AddJsonFile("appsettings.json")
    .Build();


builder.Services.AddControllers();
builder.Services.AddSingleton<IConfiguration>(builderConfig);
builder.Services.AddDbContext<UsersContext>();
builder.Services.AddScoped<IUserRepository, UserRepository>();
builder.Services.AddScoped<IUserService,UserService>();

builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowSpecificOrigin",

        builder => builder.WithOrigins("http://localhost:7080") 
                          .AllowAnyMethod()
                          .AllowAnyHeader());
});

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

//app.UseAuthorization();

app.MapControllers();

app.Run();
