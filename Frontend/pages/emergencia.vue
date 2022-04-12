<template>

      
  <v-parallax
    dark
    src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg"


  >
    <v-carousel hide-delimiters>
      <v-carousel-item
        v-for="(valor, i) in items"
        :key="i"
        :src="valor.src"
        reverse-transition="fade"
        transition="fade"
       
      >
        <div class="pt-5 text-h4 text-decoration-underline text-center">Emergencia Nº {{valor.id}}</div>
        <div class="pt-5 text-h2 font-weight-bold text-center">{{valor.titulo }}</div>
        <div class="pt-5 text-h4 text-center">Descripción: {{valor.descripcion }}</div>
        <div class="pt-5 text-h4 text-center">Dirección: {{ valor.direccion }}</div>
        <div v-if = "getStatus(valor.activo) == 'Activo'">
              <div class="pt-5 text-h4 text-center">Estado: Activo</div>
        </div>
        <div v-else>
              <div class="pt-5 text-h4 text-center">Estado: Inactivo</div>
        </div>
          <div style = "padding-top:5%; padding-left: 33%;">
            <v-btn   justify-center color="accent" elevation="5" :href = "'/nuevaTarea/' + valor.id" rounded>
              Agregar Tarea
            </v-btn>
            <v-btn   justify-center color="accent" elevation="5" :href = "'/mapa/' + valor.id" rounded>
              Ubicación de voluntarios
            </v-btn>
        </div>
      </v-carousel-item>
    </v-carousel>
  </v-parallax>
</template>

<script>
import axios from "axios";
export default {
    name:'emergencia',
  //Función que contiene los datos del componente
 data(){
        return{
            items:[],
            id_emergencia:null,
            habilidades:[],
        }
    },

  methods: {
    //Función asíncrona para consultar los datos
    getData: async function () {
      try {
        let response1 = await this.$axios.get(
          "http://localhost:8080/emergencias/"
        );
        console.log(response1);
        this.items = response1.data;
        
      } catch (error) {
        console.log("error", error);
      }
    },
     getStatus: function(value) {
        if(value){
          return "Activo"
        }else{
          return "Inactivo"
        };
    }
  },
  //Función que se ejecuta al cargar el componente
  created: function () {
    this.getData();
  },
};
</script>