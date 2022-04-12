<template>
  <div>
  <v-col>
  <v-card>
     <v-row padding-top="10"> 
     <v-col pt="10">
    <v-tabs
      dark
       color="black"
       background-color="#419cb0"
      show-arrows
    >
      <v-tabs-slider color="teal lighten-3"></v-tabs-slider>
      <v-tab
         v-for="(emer, i) in emergencias"
        :key="i"
        item-text="emer.titulo"
        @click="show(emer._id,emer.titulo,emer.descripcion,emer.direccion,emer.id_estado)">
        {{emer.titulo}}
      </v-tab>
    </v-tabs>
    </v-col>
    </v-row>

    <div>
      <v-col>
        <v-card class="mx-auto" outlined color="#77c2d1">
        <v-card-title> Emergencia: {{emerEscogida}} </v-card-title> 
        <v-card-subtitle> Descripcion: {{emerEscogidaDescrp}}</v-card-subtitle>
        <v-card-text> Direccion: {{emerEscogidaDir}}</v-card-text>
        </v-card>
      </v-col>
    </div>



    <v-row padding-top="10">
    <v-col>
     <v-tabs
      dark
       color="black"
      background-color="#6b81b5"
      show-arrows
    >
      <v-tabs-slider color="teal lighten-3"></v-tabs-slider>

      <v-tab
        v-for="(task,i) in tareas"
        item-text="task.titulo"
        :key="i"
        @click="showT(task)"
      >
          {{ task.titulo }}
      </v-tab>
    </v-tabs>
    </v-col>
    </v-row>

    <div>
        <v-col>
          <v-card class="mx-auto" outlined color="#9FA8DA">
          <v-card-title> Tarea: {{taskEscogida}} </v-card-title> 
          <v-card-subtitle> Descripcion: {{taskEscogidaDescrp}}</v-card-subtitle>
          <v-card-text> Cantidad voluntarios: {{taskEscogidaCantVol}}</v-card-text>
          </v-card>
        </v-col>
      </div>

  </v-card>
  </v-col>
  </div>
</template>


<script>
export default {
  //Función que contiene los datos del componente
  data() {
    return {
      //Lista de ítems a mostrar
      emergencias: [],
      tareas: [],
      emerEscogida: "",
      emerEscogidaDescrp: "",
      emerEscogidaDir: "",
      volEscogido: "",
      taskEscogida: "",
      taskEscogidaDescrp: "",
      taskEscogidaCantVol: "",
      id:Number,
    };
  },
  created() {
        let apiURL = 'http://localhost:8080/emergenciasMongo/';
        this.$axios.get(apiURL).then(res => {
            this.emergencias = res.data;
          console.log(res.data);
        }).catch(error => {
            console.log(error)
        });
        
        
    },
  methods: {
    //Funcion asincrona para consultar los datos
    show: async function (id,titulo,descripcion,direccion,activo) {
        this.emerEscogida=titulo;
        this.emerEscogidaDescrp=descripcion;
        this.emerEscogidaDir=direccion;
       let apiURL = 'http://localhost:8080/tareasMongo/'+id;
        this.$axios.get(apiURL).then(res => {
            this.tareas = res.data;
          console.log(res);
        }).catch(error => {
            console.log(error)
        });
    },
    showT: async function (value) {
        this.taskEscogida=value.titulo;
        this.taskEscogidaDescrp=value.descripcion;
        this.taskEscogidaCantVol=value.cant_voluntarios;
      
    },
  },


};
</script>
