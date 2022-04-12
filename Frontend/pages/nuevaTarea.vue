<template>
  <v-card max-width="600" class="mx-auto">
    <v-card-title class="justify-center"> Nueva tarea </v-card-title>
    <v-col cols="12">
      <v-form ref="form" lazy-validation>
        <v-text-field
          :value="$route.params.id"
          label="Id de Emergencia seleccionada"
          outlined
          disabled
        ></v-text-field>

        <v-text-field
          label="Titulo"
          outlined
          required
          v-model="titulo"
        ></v-text-field>

        <v-text-field
          label="Descripcion"
          v-model="descripcion"
          outlined
          required
        ></v-text-field>

        <v-text-field
          label="Número de voluntarios requeridos"
          outlined
          required
          v-model="cant_voluntarios"
        ></v-text-field>
        <v-combobox
          v-model="habilidades"
          :items="habilidad"
          item-text="nombreH"
          label="Requerimientos"
          multiple
        ></v-combobox>

        <v-btn
          v-on:click="
            crearTarea
          "
          color="success"
          class="mr-4"
          >Ingresar tarea
        </v-btn>

        <v-btn color="error" href="/emergencia" class="mr-4"> Cancelar </v-btn>
      </v-form>
    </v-col>
  </v-card>
</template>

<script>
import axios from "axios";
import Multiselect from "vue-multiselect";
export default {
  name: "nuevaTarea",
  components: {
    Multiselect,
  },
  data() {
    return {
      message: "",
      titulo: null,
      estado: false,
      cant_voluntarios: null,
      descripcion: null,
      id_emergencia: null,
      id_estado: null,
      requerimientos: [],
      habilidades: [],
      habilidad: [],
      id_eme_habilidad: null,
      id_tareaN: [],
      id_tarea:null,
    };
  },
  methods: {
    crearTarea: async function () {
      try {
         const response = await axios.post("http://localhost:8080/tareas/create/", {
            titulo: this.titulo,
            descripcion: this.descripcion,
            cant_voluntarios: this.cant_voluntarios,
            id_emergencia: this.$route.params.id,
            id_estado: 1,
          });
          console.log(response);
          this.id_tareaN=response.data;
          this.crearTareaHabilidad(this.id_tareaN);
      } catch (error) {
        //mensaje de error
        console.log("error");
      }
    },
    crearTareaHabilidad: function crearTareaHabilidad(id_tareaN){
      try {
        let requerimientos=this.habilidades;
        for(const i in requerimientos) {
          let id=requerimientos[i].id;
            axios.post("http://localhost:8080/tarea_habilidades/create/", {
            id_tarea:id_tareaN.id,
            id_eme_habilidad:id,
          });
        }
        alert("Ha creado la tarea");
      } catch(error) {
        //mensaje de error
        console.log("error");
      }
    },
    getHabilidades: function () {
      try {
        axios
          .get(
            "http://localhost:8080/habilidadesEmergencia/" +
              this.$route.params.id +
              "/"
          )
          .then(({ data }) => (this.habilidad = data));
      } catch (error) {
        console.log("error", error);
      }
    },
    mounted() {
      console.log(this.$route.params.id);
    },
  },
  created: function () {
    this.getHabilidades();
  },
};
</script>
