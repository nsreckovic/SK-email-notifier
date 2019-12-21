<template>
    <Navbar>
        <div class="mx-auto" style="width: 80%">
            <h2>My subscriptions</h2>
            <table class="table table-striped table-hover table-bordered text-center ">
                <thead>
                <tr>
                    <th>
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
                    <td>
                        <label class="form-checkbox">
                            <input type="checkbox" :value="{id:i.id, name:i.city}" v-model="selected">
                            <i class="form-icon"></i>
                        </label>
                    </td>
                    <td>{{i.city}}</td>
                    <td>{{i.country}}</td>
                    <td>{{i.temp}}°C</td>
                    <td>{{i.feels_like}}°C</td>
                    <td>{{i.pressure}}mb</td>
                    <td>{{i.humidity}}%</td>
                    <td>{{i.visibility}}m</td>
                </tr>
                </tbody>
            </table>
            <button class="btn btn-primary" @click="unsubscribe">Unsubscribe</button>
        </div>
    </Navbar>
</template>
<script>
    import Navbar from "./Navbar";
    import axios from 'axios';
    axios.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    export default {
        name: "MySubscriptions",
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
            unsubscribe: function(){
                if (!this.selectAll) {
                    axios.post('http://localhost:8081/subscription/removeSubscriptions', {email:this.$store.state.user.email, cities:this.selected}).then((response) => {
                        // eslint-disable-next-line no-console
                        this.asd = response;
                        this.$notify({
                            group: 'Notifications',
                            type: 'vue-notification success',
                            position: 'top left',
                            title: 'Success',
                            text: 'You unsubscribed successfuly.'
                        });
                        this.$router.push('/');
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
                axios.post('http://localhost:8081/subscription/getSubscribedWeather',this.$store.state.user.email).then((response) => {
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