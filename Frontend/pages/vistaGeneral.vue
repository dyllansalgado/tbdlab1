<template>
  <v-container>
    <v-row no-gutters>
      <v-col order="first">
        <div class="text-center">
          <v-menu transition="fab-transition">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                dark
                width="65%"
                height="60px"
                color="#58b7cc"
                v-bind="attrs"
                v-on="on"
              >
                Emergencias
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(emer, index) in emergencias"
                :key="index"
                @click="getTareasEmergencia(emer.id, emer.titulo)"
              >
                <div v-if="getStatus(emer.activo) == 'Activo'">
                  <v-list-item-title>
                    id: {{ emer.id }} | Titulo: {{ emer.titulo }} | Estado:
                    Activo</v-list-item-title
                  >
                </div>
                <div v-else>
                  <v-list-item-title>
                    id: {{ emer.id }} | Titulo: {{ emer.titulo }} | Estado:
                    Inactivo</v-list-item-title
                  >
                </div>
              </v-list-item>
            </v-list>
          </v-menu>
        </div>
        <div class="pt-10">
              <v-col cols="12">
          <v-textarea
          dark
            background-color="#419cb0"
            :label="emerEscogida"
              outlined
              rows="2"
              width="1%"
              row-height="20px"
              disabled
          ></v-textarea>
          </v-col>
           
        </div>
      </v-col>
      <v-col>
        <div class="text-center">
          <v-menu transition="fab-transition">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                dark
                width="65%"
                height="60px"
                color="#9FA8DA"
                v-bind="attrs"
                v-on="on"
              >
                Tareas
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(task, index) in tareas"
                :key="index"
                @click="getVoluntariosTarea(task.id, task.titulo)"
              >
                <v-list-item-title>
                  id: {{ task.id }} | Nombre: {{ task.titulo }} | Nºvoluntarios:
                  {{ task.cant_voluntarios }} | Estado: {{ task.id_estado }}
                </v-list-item-title>
              </v-list-item>
            </v-list>
          </v-menu>
          <div class="pt-10">
              <v-col cols="12">
            <v-textarea
             dark
              background-color="#6b81b5"
              :label="taskEscogida"
              outlined
              rows="2"
              width="1%"
              row-height="20px"
              disabled
            ></v-textarea>
          </v-col>
          </div>
        </div>
      </v-col>
      <v-col order="last">
        <div class="text-center">
          <v-menu transition="fab-transition">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                dark
                width="65%"
                height="60px"
                color="#ea899a"
                v-bind="attrs"
                v-on="on"
              >
                Voluntarios
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(vol, index) in voluntarios"
                :key="index"
                @click="showVol(vol.nombre)"
              >
                <div v-if="getStatus(vol.disponibilidad) == 'Activo'">
                  <v-list-item-title>
                    RUT: {{ vol.rut }} | Nombre: {{ vol.nombre }} |
                    Disponibilidad: Disponible</v-list-item-title
                  >
                </div>
                <div v-else>
                  <v-list-item-title>
                    RUT: {{ vol.rut }} | Nombre: {{ vol.nombre }} |
                    Disponibilidad: No disponible</v-list-item-title
                  >
                </div>
              </v-list-item>
            </v-list>
          </v-menu>
          <div class="pt-10">
           <v-col cols="12">
            <v-textarea
              background-color="#d96f81"
              :label=volEscogido
              outlined
              rows="2"
              width="1%"
              row-height="20px"
              dark
              disabled
            ></v-textarea>
            </v-col>
          </div>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  //Función que contiene los datos del componente
  data() {
    return {
      //Lista de ítems a mostrar
      emergencias: [],
      tareas: [],
      voluntarios: [],
      emerEscogida: "",
      volEscogido: "",
      taskEscogida: "",
    };
  },

  methods: {
    //Funcion asincrona para consultar los datos
    getEmergencia: async function () {
      try {
        let response = await this.$axios.get(
          "http://localhost:8080/emergencias/"
        );
        this.emergencias = response.data;
        console.log(response);
      } catch (error) {
        console.log("error", error);
      }
    },

    getTareasEmergencia: async function (id, titulo) {
      this.emerEscogida = titulo;
      try {
        let response2 = await this.$axios.get(
          "http://localhost:8080/tareasEmergencias/" + id + "/"
        );
        this.tareas = response2.data;
        console.log(response2);
      } catch (error) {
        console.log("error", error);
      }
    },

    getVoluntariosTarea: async function (id, titulo) {
      this.taskEscogida = titulo;
      try {
        let response2 = await this.$axios.get(
          "http://localhost:8080/voluntariosTareas/" + id + "/"
        );
        this.voluntarios = response2.data;
        console.log(response2);
      } catch (error) {
        console.log("error", error);
      }
    },
    getStatus: function (value) {
      if (value) {
        return "Activo";
      } else {
        return "Inactivo";
      }
    },
    showVol: async function (value) {
      this.volEscogido = value;
    },
  },

  //Función que se ejecuta al cargar el componente
  created: function () {
    this.getEmergencia();
  },
};
</script>
