<template>
    <Navbar>
        <div class="mx-auto" style="width: 80%">
            <h2>Current weather</h2>
            <table class="table table-striped table-hover table-bordered text-center ">
                <thead>
                <tr>
                    <th v-if="$store.state.loggedIn===true">
                        <label class="form-checkbox">
                            <input type="checkbox" v-model="selectAll" @click="select">
                            <i class="form-icon"></i>
                        </label>
                    </th>
                    <th>City</th>
                    <th>Country</th>
                    <th>Temperature</th>
                    <th>Feels like</th>
                    <th>Pressure</th>
                    <th>Humidity</th>
                    <th>Visibility</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="i in items" v-bind:key="i.id">
                    <td v-if="$store.state.loggedIn===true">
                        <label class="form-checkbox">
                            <input type="checkbox" :value="{id:i.id, name:i.city}" v-model="selected">
                            <i class="form-icon"></i>
                        </label>
                    </td>
                    <td>{{i.city}}</td>
                    <td>{{i.country}}</td>
                    <td>{{Math.floor(i.temp)}}°C</td>
                    <td>{{Math.floor(i.feels_like)}}°C</td>
                    <td>{{Math.floor(i.pressure)}}mb</td>
                    <td>{{Math.floor(i.humidity)}}%</td>
                    <td>{{Math.floor(i.visibility)}}m</td>
                </tr>
                </tbody>
            </table>
            <button v-if="$store.state.loggedIn===true" class="btn btn-primary" @click="subscribe">Subscribe</button>
        </div>
    </Navbar>
</template>
<script>
    import Navbar from "./Navbar";
    import axios from 'axios';
    axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    export default {
        name: "HomePage",
        components: {
            Navbar
        },
        data: () => ({
            items: [],
            selected: [],
            selectAll: false,
            request: null
        }),
        methods: {
            subscribe: function(){
                if (!this.selectAll) {
                    axios.post('http://localhost:9000/s1/subscription/saveSubscriptions', {email:this.$store.state.user.email, cities:this.selected}).then((response) => {
                        this.asd = response;
                        this.$router.push('/mySubscriptions');
                        this.$notify({
                            group: 'Notifications',
                            type: 'vue-notification success',
                            position: 'top left',
                            title: 'Success',
                            text: 'Subscription successfuly.'
                        });
                    }).catch((error) => {
                        this.$notify({
                            group: 'Notifications',
                            type: 'vue-notification error',
                            title: 'Initialise exception',
                            text: error
                        });
                    });
                }
            },
            select:function() {
                this.selected = [];
                if (!this.selectAll) {
                    for (let i in this.items) {
                        this.selected.push({id:this.items[i].id, name:this.items[i].city});
                    }
                }
            },
            init: function(){
                if (this.$store.state.loggedIn){
                    axios.post('http://localhost:9000/s1/subscription/getNotSubscribedWeather',this.$store.state.user.email).then((response) => {
                        this.items = response.data;
                    }).catch((error) => {
                        this.$notify({
                            group: 'Notifications',
                            type: 'vue-notification error',
                            title: 'Initialise exception',
                            text: error
                        });
                    });
                } else {
                    axios.get('http://localhost:9000/s2/weather/findAll').then((response) => {
                        this.items = response.data;
                    }).catch((error) => {
                        this.$notify({
                            group: 'Notifications',
                            type: 'vue-notification error',
                            title: 'Initialise exception',
                            text: error
                        });
                    });
                }
            }
        },
        beforeMount(){
            this.init()
        },
    }
</script>
<style>
    h2{
        padding-top: 1%;
        padding-bottom: 1%
    }
</style>