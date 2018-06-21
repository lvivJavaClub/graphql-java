GraphQL with Java
---

[http://localhost:8080/graphiql](http://localhost:8080/graphiql) to start executing queries. For example:
```
mutation createnewuser($username:String) {
  createUser(id:2 , name: $username) {
    name
  }
}

query getuser{
  user(id:2) {
    name
  }
}
```


with variables:

```
{
  "username": "user name"
}
```
