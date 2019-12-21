<template>
    <Navbar>
        <div class="mx-auto" style="width: 800px;">
            <h2>Login</h2>
            <form @submit.prevent="submit">
                <div class="form-group">
                    <label for="InputEmail">Email:</label>
                    <input type="text" v-model="form.email" class="form-control" id="InputEmail" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="InputPassword">Password:</label>
                    <input type="password" v-model="form.password" class="form-control" id="InputPassword" placeholder="Enter password">
                </div>
                <button class="btn btn-primary" type="submit">Login</button>
            </form>
        </div>
    </Navbar>
</template>
<script>
    import Navbar from "./Navbar";
    import axios from 'axios';
    axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    export default {
        name: "Login",
        components: {
            Navbar
        },
        data: function() {
            return {
                form: {
                    email: null,
                    password: null
                }
            }
        },
        methods: {
            submit: function () {
                axios.post('http://localhost:8081/user/login', this.form).then((response) => {
                    // eslint-disable-next-line no-console
                    if (typeof response.data.email === 'undefined') {
                        this.$notify({
                            group: 'Notifications',
                            type: 'vue-notification error',
                            title: 'Login exception',
                            text: 'Wrong email or password!'
                        });
                    } else {
                        this.$store.commit('set_user', response.data);
                        this.$router.push('/');
                        this.$notify({
                            group: 'Notifications',
                            type: 'vue-notification success',
                            position: 'top left',
                            title: 'Success',
                            text: 'You logged in successfuly.'
                        });
                    }

                }).catch((error) => {
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification error',
                        title: 'Login exception',
                        text: error
                    });
                });
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