<template>
  <v-card max-width="600" class="mx-auto">
    <v-card-title class="font-weight-bold justify-center"> Editar tarea </v-card-title>
    
    <v-card-text class="font-weight-medium justify-center">Ingrese nuevos valores a los campos que quiera cambiar de la tarea seleccionada:
    </v-card-text>
    <v-col cols="12">
      <v-form ref="form" lazy-validation>
        <v-row align="center">
          <v-col cols="3">
        <v-subheader>Titulo:</v-subheader>
      </v-col>
        <v-col cols="9">
          <v-text-field
            v-model="titulo"
            :label="task.titulo"
          ></v-text-field>
          </v-col>
        </v-row>

        <v-row align="center">
          <v-col cols="3">
        <v-subheader>Descripción:</v-subheader>
      </v-col>
      <v-col cols="9">
          <v-text-field
            v-model="descripcion"
            :label="task.descripcion"
          ></v-text-field>
          </v-col>
        </v-row>

        <v-row align="center">
        <v-col cols="3">
        <v-subheader>Nº de Voluntarios:</v-subheader>
      </v-col>
      <v-col cols="9">
        <v-text-field
          v-model.number="cant_voluntarios"
          :placeholder = "task.cant_voluntarios"
        ></v-text-field>
        </v-col>
       </v-row>

        <v-row align="center">
          <v-col cols="3">
        <v-subheader>Estado: 1 Activo / 2 Inactivo</v-subheader>
        </v-col>
        <v-col cols="9">
          <v-text-field 
          :placeholder = "task.id_estado"
          v-model.number="id_estado"
        > </v-text-field>
    
        </v-col>
        </v-row>

      
        
        <v-btn v-on:click="putTarea" color="success" class="mr-4"
          >Actualizar tarea
        </v-btn>

        <v-btn color="error" href="/tareas" class="mr-4"> Cancelar </v-btn>
      </v-form>
    </v-col>
  </v-card>
</template>

<script>
import axios from "axios";
export default {
  name: "editarTarea",
  data() {
    return {
      task: [],
      titulo: null,
      descripcion: null,
      cant_voluntarios: null,
      id_estado: null,
    };
  },
  methods: {

    //Funcion asincrona para consultar los datos
    getData: async function () {
      try {
        let response2 = await this.$axios.get(
          "http://localhost:8080/tareas/" + this.$route.params.id
        );
        console.log(response2);
        this.task = response2.data;
        this.task.cant_voluntarios= this.task.cant_voluntarios.toString(); 
        this.task.id_estado= this.task.id_estado.toString(); 
      } catch (error) {
        console.log("error", error);
      }
    },

    putTarea: async function () {
      try {
        const response = await axios.put(
          "http://localhost:8080/tareas/" + this.$route.params.id,
          {
            titulo: this.titulo,
            descripcion: this.descripcion,
            cant_voluntarios: this.cant_voluntarios,
            id_emergencia: this.$route.params.id,
            id_estado: this.id_estado,
          }
        );
        console.log(response);
        alert("Se ha actualizado la tarea exitosamente");
      } catch (error) {
        //mensaje de error
        console.log("error");
      }
    },
    mounted() {
      console.log(this.$route.params.id);
    },
  },
  //Funcion que se ejecuta al cargar el componente
  created: function () {
    this.getData();
  },
};
</script>
