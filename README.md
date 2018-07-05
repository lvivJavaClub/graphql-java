GraphQL with Java
---

[http://localhost:8080/graphiql](http://localhost:8080/graphiql) to start executing queries. For example:
```
mutation createnewuser1($username1: String) {
  createUser(id: 1, name: $username1) {
    name
  }
}

mutation createnewuser2($username2: String) {
  createUser(id: 2, name: $username2) {
    name
  }
}

mutation createnewuser3($username3: String) {
  createUser(id: 3, name: $username3) {
    name
  }
}

mutation friends12 {
 createFriends(id1: 1, id2: 2)
}

mutation friends23 {
 createFriends(id1: 2, id2: 3)
}

query getuser1 {
  user(id: 1) {
    id
    name
    friends{
      id
      name
      friends {
        name
        friends {
          id
          friends {
            name
          }
        }
      }
    }
  }
}

query getuser2 {
  user(id: 2) {
    id
    name
    friends {
      id
    }
  }
}

query getuser3 {
  user(id: 3) {
    id
    name
  }
}

```


with variables:

```
{
  "username1": "user name 1",
  "username1": "user name 2",
  "username1": "user name 3"
}
```
