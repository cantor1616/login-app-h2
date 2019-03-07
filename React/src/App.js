import React from "react";

import Titles from "./components/Titles";
import User from "./components/User";
import axios from 'axios';

class App extends React.Component {
  constructor(){
    super();
    this.state = {
      result: undefined,
      username: "",
      password: "",
    };
    this.handleChange = this.handleChange.bind(this);
  }

  render() {
    return (
      <div>
        <div className="wrapper">
          <div className="main">
            <div className="container-fluid">
              <div className="row">
                <div className="col-5 title-container">
                  <Titles />
                </div>
                <div className="col-7 form-container">
                  <input 
                    type = "text"
                    name="username" 
                    placeholder="Username..."
                    value={this.state.username} 
                    onChange={this.handleChange}
                  />
                  <input 
                    type = "text" 
                    name="password" 
                    placeholder="Password..."
                    value={this.state.password} 
                    onChange={this.handleChange} 
                  />
                  <button onClick={(event) => {this.signIn(this.state.username, this.state.password)}}>Sign-in</button>
                  <button onClick={(event) => {this.create(this.state.username, this.state.password)}}>Create</button>
                  <button onClick={(event) => {this.userList()}}>User List</button>
                  <button onClick={(event) => {this.delete(this.state.username, this.state.password)}}>Delete</button>
                  <User 
                    result={this.state.result}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  handleChange({target}){
    this.setState({[target.name]: target.value});
  }

  usernameAndPassword(username, password){
    //make sure both username and password are given
    if (!username || !password){
      this.setState({result: "Username and/or password not entered."});
      return false;
    }
    return true;
  }

  isEmpty(obj) {
    for(var key in obj) {
        if(obj.hasOwnProperty(key)){
            return false;
        }
    }
    return true;
  }

  userList(){
    //display all usernames
    axios.get(`http://localhost:8080/users`)
    .then((response) => {
      console.log(JSON.stringify(response));
      if (this.isEmpty(response.data)){
        this.setState({result: "No users created yet."});
      } else {
        this.setState({result: <div><p/>
          <p>{"Username, Password"}</p>
          {response.data.map(obj => 
          <li key = {obj.username}>{obj.username + ", " + obj.password}</li>)}
        </div>});
      }
    })
  }
  
  signIn(username, password){
    //make sure fields are filled out
    if (!this.usernameAndPassword(username, password)){
      return;
    };
    //signIn
    axios.get(`http://localhost:8080/users/login/${username}/${password}`)
    .then((response) => {
      if (response.data){
        this.setState({result: "Login successful!"});
      } else {
        this.setState({result: "Username does not exist. Please select \"Create\" to make a new account."});
      }
      console.log(response);
    })
    .catch(function(error){
      console.log(error);
    });
  }

  create(username, password){
    //make sure fields are filled out
    if (!this.usernameAndPassword(username, password)){
      return;
    };
    //create new user
    axios.post(`http://localhost:8080/users/add/${username}/${password}`)
    .then((response) => {
      if (response.data){
        this.setState({result: "New user created successfully!"});
      } else {
        this.setState({result: "Username already exists. Please select \"Sign-In\" to login."});
      }
      console.log(response);
    })
    .catch(function(error){
      console.log(error);
    });
  }
   
  delete(username, password){
    //make sure fields are filled out
    if (!this.usernameAndPassword(username, password)){
      return;
    };
    //delete user
    axios.delete(`http://localhost:8080/users/delete/${username}/${password}`)
    .then((response) => {
      if (response.data){
        this.setState({result: username + " deleted."})
      } else {
        this.setState({result: "Username and/or password either does not exist or was entered incorrectly."})
      }
    })
    .catch(function(error){
      console.log(error);
    });
  }
}

export default App;
        