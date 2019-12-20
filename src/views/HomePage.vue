<template>
    <Navbar>
        <div class="mx-auto" style="width: 800px;">
            <h1>Home Page</h1>
        </div>
    </Navbar>
</template>
<script>
    import axios from 'axios'
    import Navbar from "./Navbar";
    export default {
        name: "HomePage",
        components: {
            Navbar
        },
        data: () => {
            return {
                posts: []
            }
        },
        mounted() {
            axios.get('http://localhost:3000/api/posts').then((res) => {
                this.posts = res.data.posts;
            });
        }
    }
</script>