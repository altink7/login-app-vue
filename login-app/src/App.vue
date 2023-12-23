<template>
  <div class="app">
    <h1>Login</h1>
    <form @submit.prevent="loginUser">
      <div>
        <label class="form-label" for="username">User Name/E-Mail</label>
        <input v-model="email" type="text" class="form-control" id="username" placeholder="Enter your username or email">
      </div>
      <div class="form-group mt-3">
        <label class="form-label" for="password">Password</label>
        <input v-model="password" type="password" class="form-control" id="password" placeholder="Enter your password">
      </div>
      <div class="form-actions">
        <br>
        <button type="submit" class="btn btn-primary">Login</button>
        <hr>
        <button @click="registerUser" class="btn btn-secondary" type="button">Register</button>
      </div>
      <hr>
      <div class="google-login">
        <GoogleLogin :callback="callback" />
      </div>
    </form>

    <hr>
    <div v-if="isLogged">
      <h1>Logged in</h1>
      <h1>{{ username }}</h1>
    </div>

  </div>
</template>

<script>
import {decodeCredential, GoogleLogin} from "vue3-google-login";
import axios from "axios";

export default {
  name: 'LoginApp',
  components: {
    GoogleLogin
  },
  data() {
    return {
      email: "",
      username: "",
      password: "",
      isLogged: false
    }
  },
  methods: {
    async loginUser() {
      console.log("Login user", this.email, this.password);

      await axios.get("http://localhost:5000/api/login/user/" + this.email + "/password/" + this.password)
          .then(response => {
            console.log("Response", response);
            this.username = response.data;
            if (this.username !== "") {
              this.isLogged = true;
            }
          })
          .catch(error => {
            console.log("Error", error);
          });
    }
    ,
    async registerUser() {
      console.log("Register user", this.email, this.password);

      await axios.post("http://localhost:5000/api/register", {
        username: this.email,
        password: this.password
      });

    },
    callback(response) {
      const userData = decodeCredential(response.credential);

      this.username = userData.email;
      if (this.username !== "") {
        this.isLogged = true;
      }
      console.log("User data", userData);
    }
  }
}

</script>

<style scoped>
h1 {
  text-align: center;
}

.app {
  width: 50%;
  margin: 0 auto;
  align-content: center;
}

.form-label {
  font-weight: bold;
}

.form-actions {
  text-align: center;
}

.google-login {
  text-align: center;
}

</style>