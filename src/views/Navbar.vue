<template>
    <div>
        <nav class="pl-5 pr-5 navbar navbar-expand-lg navbar-light bg-light">
            <router-link class="navbar-brand" to="/">PC Blog</router-link>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-expanded="false">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul v-if="$store.state.loggedIn===true" class="navbar-nav navbar-left">
                    <li class="nav-item">
                        <router-link class="nav-link" to="/posts/add">Add Post<span class="sr-only">(current)</span></router-link>
                    </li>
                </ul>
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
    export default {
        name: "Navbar",
        methods:{
            Logout:function(){
                /*
                axios.post('http://localhost:3000/api/users/logout').then((response) => {
                    this.$store.commit('remove_user');
                    //console.log(this.$store.state);
                    this.$index.push('/users/login');
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification success',
                        position: 'top left',
                        title: 'Success',
                        text: 'You logged out successfuly.'
                    });
                }).catch((error) => {
                    this.$notify({
                        group: 'Notifications',
                        type: 'vue-notification error',
                        title: 'Logout exception',
                        text: error
                    });
                });
                */
            }
        }
    }
</script>