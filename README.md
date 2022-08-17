# REST API e-Commerce

# Features

- Categories
- Products
- Suppliers
- Shopping Cart
- Cart Item
- Coupon
- Wish List

# Technologies

- Java JDK 17
- Maven version 3.8.6
- Spring Boot version 2.7.2
- Spring JPA
- Spring Security
- Json Web Token (JWT)
- PostgreSQL

# Noted

- Kurang melengkapi Security dan JWT
- Kita akan menggunakan sistem JWT
- Jadi kita perlu membuat TokenProvider, JwtEntryPoint
- Kita contoh project product-restful-api
- Di project product-restful-api kita membutuhkan 3 class untuk JWT beroperasi, yakni: JwtTokenProvider, JwtAuthenticationEntryPoint, dan JwtAuthenticationFilter
- Nanti kita bisa melakukan custom response antara lain:
- Unauthorized (JwtAuthenticationEntryPoint), ini untuk menghandle jika ternyata username dan password kita tidak dikenali oleh aplikasi kita
- AccessDenied (AccessDeniedHandler), ini untuk menghanlde jika ternyata user yang sudah login tidak memiliki ijin akses resourse/API nya
- JwtAuthenticationFilter berfungsi untuk mengecek apakah request dari client membawa token atau tidak, dan token nya bernama Bearer atau tidak