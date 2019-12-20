<template>
    <Navbar>
        <div class="mx-auto" style="width: 800px;">
            <h1>Home Page</h1>
        </div>
    </Navbar>
</template>
<script>
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

        }
    }
</script>