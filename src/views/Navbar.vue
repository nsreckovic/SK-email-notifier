<template>
    <div>
        <nav class="pl-5 pr-5 navbar navbar-expand-lg navbar-light bg-light">
            <router-link class="navbar-brand" to="/">NP Weather</router-link>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-expanded="false">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <template v-if="$store.state.loggedIn===false">
                        <li class="nav-item">
                            <router-link class="nav-link" to="/users/register">Register<span class="sr-only">(current)</span></router-link>
                        </li>
                        <li class="nav-item">
                            <router-link class="nav-link" to="/users/login">Login<span class="sr-only">(current)</span></router-link>
                        </li>
                    </template>
                    <template v-else>
                        <li class="nav-item">
                            <a @click="Logout" class="nav-link" href="#">Logout<span class="sr-only">(current)</span></a>
                        </li>
                    </template>
                </ul>
            </div>
        </nav>
        <slot>

        </slot>
    </div>
</template>

<script>
    import axios from 'axios';
    axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    export default {
        name: "Navbar",
        methods:{
            Logout:function(){
                this.$store.commit('remove_user');
                this.$router.push('/users/login');
                this.$notify({
                    group: 'Notifications',
                    type: 'vue-notification success',
                    position: 'top left',
                    title: 'Success',
                    text: 'You logged out successfuly.'
                });
            }
        }
    }
</script>