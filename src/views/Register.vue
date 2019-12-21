<template>
    <Navbar>
        <div class="mx-auto" style="width: 800px;">
            <h2>Register</h2>
            <form @submit.prevent="submit">
                <div class="form-group">
                    <label for="InputName">Name:</label>
                    <input v-model="form.name" type="text" class="form-control" id="InputName" placeholder="Enter name">
                </div>
                <div class="form-group">
                    <label for="InputSurname">Surname:</label>
                    <input v-model="form.surname" type="text" class="form-control" id="InputSurname" placeholder="Enter surname">
                </div>
                <div class="form-group">
                    <label for="InputEmail">Email:</label>
                    <input v-model="form.email" type="text" class="form-control" id="InputEmail" placeholder="Enter email">
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
                    surname: null,
                    email: null,
                    password: null,
                    password2: null
                }
            }
        },
        methods: {
            submit: function() {
                if (!this.form.name || !this.form.surname || !this.form.email || !this.form.password || !this.form.password2) {
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification error',
                        title: 'Register exception',
                        text: 'Please fill in all fields!'
                    });
                } else {
                    if (this.form.password === this.form.password2) {
                        axios.post('http://localhost:8081/user/save', this.form).then((response) => {
                            this.asd = response;
                            this.$router.push('/users/login');
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
                    } else {
                        this.$notify({
                            group: 'Notifications',
                            type: 'vue-notification error',
                            title: 'Register exception',
                            text: 'Passwords must match!'
                        });
                    }
                }
            }
        }
    }
</script>
<style>
    h2{
        padding-top: 1%;
        padding-bottom: 1%
    }
</style>