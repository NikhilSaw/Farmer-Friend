# FarmerFriend: Empowering Farmers with Technology

The FarmerFriend website is designed to support and empower farmers by providing a comprehensive platform for managing farming equipment, connecting with other farmers, and accessing vital information.

## Key Features

- **Rental Management**: Farmers can easily rent out their equipment to others, maximizing the utility and profitability of their resources.
- **Equipment Management**: An organized system for listing and maintaining details about farming equipment, ensuring that farmers have access to the tools they need.
- **Post and Comment System**: Farmers can share knowledge, ask questions, and provide support to each other through posts and comments, fostering a strong community.
- **User Management**: Secure and efficient handling of user data to ensure a safe and personalized experience for all farmers.

By leveraging these features, the FarmerFriend website aims to enhance collaboration, resource sharing, and knowledge exchange among farmers, ultimately contributing to more efficient and productive farming practices.

## Functional Requirements

- **Rental Management**: Save and manage rental details for farming equipment.
- **Comment Management**: Handle comments related to posts.
- **Equipment Management**: Manage farming equipment data.
- **Post Management**: Handle CRUD operations for posts.
- **User Management**: Manage user data and authentication.

## System Design

### Architectural Design

The system follows a RESTful API architecture using Spring Boot. Each controller handles specific parts of the application. The services layer implements the business logic, and the data access layer interacts with the database.

### Components

- **Controllers**: Manage HTTP requests and responses.
- **Services**: Contain business logic.
- **Entities**: Represent database tables.
- **Repositories**: Interface with the database.

## Implementation (Overview of Methods with Mappings)

### RentalController

- **Method**: saveRentalDetails
  - **Mapping**: `@PostMapping("/saveRental")`

### CommentController

- **Method**: addComment
  - **Mapping**: `@PostMapping("/addComment")`
- **Method**: getCommentsByPostId
  - **Mapping**: `@GetMapping("/getComments")`
- **Method**: deleteComment
  - **Mapping**: `@DeleteMapping("/deleteComment")`

### EquipmentController

- **Method**: addEquipment
  - **Mapping**: `@PostMapping("/addEquipment")`
- **Method**: getAllEquipments
  - **Mapping**: `@GetMapping("/getAllEquipments")`
- **Method**: updateEquipment
  - **Mapping**: `@PutMapping("/updateEquipment")`
- **Method**: deleteEquipment
  - **Mapping**: `@DeleteMapping("/deleteEquipment")`

### PostController

- **Method**: addPost
  - **Mapping**: `@PostMapping("/addPost")`
- **Method**: getAllPosts
  - **Mapping**: `@GetMapping("/getAllPosts")`
- **Method**: updatePost
  - **Mapping**: `@PutMapping("/updatePost")`
- **Method**: deletePost
  - **Mapping**: `@DeleteMapping("/deletePost")`

### UserController

- **Method**: registerUser
  - **Mapping**: `@PostMapping("/registerUser")`
- **Method**: loginUser
  - **Mapping**: `@PostMapping("/loginUser")`
- **Method**: getAllUsers
  - **Mapping**: `@GetMapping("/getAllUsers")`
- **Method**: deleteUser
  - **Mapping**: `@DeleteMapping("/deleteUser")`

## Testing

### Tool

- **Postman**

### Purpose

- Test the APIs to ensure they work as expected.

### Procedure

1. Create requests in Postman for each endpoint.
2. Send requests with valid and invalid data.
3. Verify the responses and status codes.

---

This README provides an overview of the FarmerFriend project, including its features, functional requirements, system design, and implementation details. The testing section outlines the steps to validate the API endpoints using Postman. For more detailed information, please refer to the documentation provided with the project.
