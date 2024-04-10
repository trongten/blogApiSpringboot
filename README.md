## Description
This personal Blog application is built using Spring Boot along with Spring Data JPA for data persistence, Spring Security for authentication and authorization, and MySQL as the database backend. It allows administrators to post articles and readers to comment under each article, featuring functionalities like login, article management, and interaction through comments.
### Auth

| Method | Url | Decription | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /api/auth/signup | Sign up | [JSON](#signup) |
| POST   | /api/auth/signin | Log in | [JSON](#signin) |

### Users

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users/{id} | Get logged in user profile | |
| PUT    | /api/users/{id} | Update user |[JSON](#updateuser) |
| DELETE | /api/users/{id} | Delete user (For logged in user or admin) | |

### Posts

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/post | Get all posts | |
| GET    | /api/post/{id} | Get post by id | |
| POST   | /api/post | Create new post (By logged in user) | [JSON](#postcreate) |
| PUT    | /api/post/{id} | Update post | [JSON](#postupdate) |
| DELETE | /api/post/{id} | Delete post | |

### Comments

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/comment | Get all comments which belongs to post with id = postId | |
| GET    | /api/comment/{id} | Get comment by id if it belongs to post with id = postId | |
| POST   | /api/comment | Create new comment for post with id = postId (By logged in user) | [JSON](#commentcreate) |
| PUT    | /api/comment/{id} | Update comment | [JSON](#commentupdate) |
| DELETE | /api/comment/{id} | Delete comment | |

##### <a id="signup">Sign Up -> /api/auth/signup</a>
```json
{
    "name": "Phan Vo Trong",
    "email":"trongnweogwe@gmail.com",
	  "username": "trongten112944",
    "password":"Password100"
}
```

##### <a id="signin">Log In -> /api/auth/signin</a>
```json
{
	  "username": "trongten112944",
    "password":"Password100"
}
```

##### <a id="userupdate">Update User -> /api/users/{username}</a>
```json
{
    "name": "Phan Vo Trong",
    "password":"Password100"
}
```

##### <a id="postcreate">Create Post -> /api/posts</a>
```json
{
	"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
	"content": "quia et suscipit suscipit recusandae consequuntur expedita et cum"
}
```

##### <a id="postupdate">Update Post -> /api/posts/{id}</a>
```json
{
	"title": "UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED",
	"content": "UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED "
}
```

##### <a id="commentcreate">Create Comment -> /api/posts/{postId}/comments</a>
```json
{
  "postId": 1,
	"content": "laudantium enim quasi est quidem magnam voluptate ipsam eos tempora quo "
}
```

##### <a id="commentupdate">Update Comment -> /api/posts/{postId}/comments/{id}</a>
```json
{
  "postId": 1,
	"content": "UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED  "
}
```
