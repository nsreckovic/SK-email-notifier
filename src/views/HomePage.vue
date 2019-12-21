<template>
    <Navbar>
        <div class="mx-auto" style="width: 1500px;">
            <h1>Current Weather</h1>

            <h5>Selected: {{selected}}</h5>
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
                            <input type="checkbox" :value="i.city" v-model="selected">
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
            <button class="btn btn-primary" @click="subscribe">Subscribe</button>
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
                //TODO make POST request (add selected cities as subscriptions)
            },
            select:function() {
                this.selected = [];
                if (!this.selectAll) {
                    for (let i in this.items) {
                        this.selected.push(this.items[i].city);
                    }
                }
            },
            init: function(){
                axios.get('http://localhost:8082/weather/findAll', this.form).then((response) => {
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
    body{
        padding: 50px
    }
</style>