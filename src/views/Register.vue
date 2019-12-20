<template>
    <Navbar>
        <div class="mx-auto" style="width: 800px;">
            <h1>Register</h1>
            <form @submit.prevent="submit">
                <div class="form-group">
                    <label for="InputName">Name:</label>
                    <input v-model="form.name" type="text" class="form-control" id="InputName" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label for="InputEmail">Email:</label>
                    <input v-model="form.email" type="text" class="form-control" id="InputEmail" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="InputUsername">Username:</label>
                    <input v-model="form.username" type="text" class="form-control" id="InputUsername" placeholder="Enter username">
                </div>
                <div class="form-group">
                    <label for="InputPassword">Password:</label>
                    <input v-model="form.password" type="password" class="form-control" id="InputPassword" placeholder="Enter password">
                </div>
                <div class="form-group">
                    <label for="InputConfirmPassword">Confirm password:</label>
                    <input v-model="form.password2" type="password" class="form-control" id="InputConfirmPassword" placeholder="Confirm password">
                </div>
                <button class="btn btn-primary" type="submit">Register</button>
            </form>
        </div>
    </Navbar>
</template>
<script>
    import Navbar from "./Navbar";
    import axios from 'axios'
    export default {
        name: "Register",
        components: {
            Navbar
        },
        data: () => {
            return {
                form: {
                    name: null,
                    email: null,
                    username: null,
                    password: null,
                    password2: null
                }
            }
        },
        methods: {
            submit: function() {
                axios.post('http://localhost:3000/api/users/register', this.form).then((response) => {
                    //console.log(response);
                    this.asd = response;
                    this.$router.push('/');
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification success',
                        position: 'top left',
                        title: 'Success',
                        text: 'You registered successfuly.'
                    });
                }).catch((error) => {
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification error',
                        title: 'Register exception',
                        text: error
                    });
                });
            }
        }
    }
</script>