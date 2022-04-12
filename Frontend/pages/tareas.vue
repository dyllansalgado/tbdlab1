<template>
    <v-container grid-list-md>
    <div v-for="(item, index) in items" :key="index">
        <v-card class="mx-auto">
            <v-card-title>Emergencia {{item.id}}</v-card-title>
            <div v-for="(task, index) in tasks"
                :key="index" 
                >
            <div v-if = "item.id === task.id_emergencia">
            <v-row>
                <v-col>
            <v-card
            class="mx-auto"
            
            outlined
            img = "https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg">
        
            
            <v-card-title primary-title class="justify-center">{{task.titulo}}
                </v-card-title>
                        
                <v-card-text class="font-weight-bold text-center">{{task.descripcion}}
                </v-card-text>
                         
                <v-card-text class="font-weight-bold text-center">Voluntarios requeridos: {{task.cant_voluntarios}}
                </v-card-text>

                <div v-if = "task.id_estado === 1">
                    <v-card-text class="font-weight-bold text-center">Estado: Activa
                </v-card-text>
                </div>
                <div v-else>
                    <v-card-text class="font-weight-bold text-center">Estado: Inactiva
                </v-card-text>
                </div>
                
            <v-card-actions>
            <v-btn
                block
                outlined
                :href = "'/editarTarea/' + task.id"
            >
                Editar
            </v-btn>
            </v-card-actions>
            
            
        </v-card>
                </v-col>
            </v-row>
        </div> <!-- DIV DEL V-IF -->
            </div> <!-- DIV DEL V-FOR TASKS -->
    </v-card>
     </div>  <!-- DIV DEL V-FOR ITEMS -->
  </v-container>
</template>

<script>
    export default {
    //Función que contiene los datos del componente
    data() {
        return {
            //Lista de ítems a mostrar
            items: [],
            tasks: [],
        };
    },
    methods: {
        //Funcion asincrona para consultar los datos
        getData: async function () {
            try {
                let response = await this.$axios.get(
                "http://localhost:8080/emergencias/"
            );
            this.items = response.data;
            console.log(response);
        } catch (error) {
            console.log("error", error);
        }
        try {
                let response2 = await this.$axios.get(
                "http://localhost:8080/tareas/"
            );
            this.tasks = response2.data;
            console.log(response2);
            } 
            catch (error) {
            console.log("error", error);
            }
        try {
                let response3 = await this.$axios.get(
                "http://localhost:8080/estado_tareas/"
            );
            this.status = response3.data;
            console.log(response3);
            } 
            catch (error) {
            console.log("error", error);
            }
        },
    },

    //Función que se ejecuta al cargar el componente
    created: function () {
        this.getData();
    },
}
</script>
