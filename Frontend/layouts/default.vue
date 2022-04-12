<template>
  <v-app dark>
    
      <v-main>
 
  <v-card>
    <v-toolbar
      dark
    >
      <v-toolbar-title class="flex text-center">SISTEMA DE VOLUNTARIADO</v-toolbar-title>
      <template v-slot:extension>
        <v-tabs
         
          align-with-title
        >
          <v-tabs-slider color="cyan"></v-tabs-slider>

          <v-tab
            v-for="(item,i) in items"
            :key="i"
            :to="item.to"
          >
            {{ item.title }}
          </v-tab>
        </v-tabs>
      </template>
    </v-toolbar>


    
  </v-card>
<v-container>
        <Nuxt />
      </v-container>
      </v-main>
    
    <v-footer
      app
    >
      <v-col
      class="text-center"
      cols="12"
    >
      <strong>Grupo 3 - Taller de Bases de Datos</strong>
      <v-spacer />
      2 - 2021 
    </v-col>
    </v-footer>
  </v-app>
</template>

<script>
export default {
  name: 'DefaultLayout',
  data () {
    return {
      clipped: false,
      drawer: false,
      fixed: false,
      items: [
        {
          icon: 'mdi-home',
          title: 'Inicio',
          to: '/'
        },
        {
          icon: 'mdi-alert',
          color: 'white',
          title: 'Emergencias',
          to: '/emergencia'
        },
        {
          icon: 'mdi-bookmark-check',
          color: 'teal darken-2',
          title: 'Tareas',
          to: '/tareas'
        },
        {
          icon: 'mdi-bookmark-check',
          color: 'teal darken-2',
          title: 'Vista general',
          to: '/vistaGeneral'
        },

        {
          icon: 'mdi-bookmark-check',
          color: 'teal darken-2',
          title: 'Mongo',
          to: '/mongo'
        },
      ],
      miniVariant: false,
      right: true,
      rightDrawer: false,
      title: 'Vuetify.js'
    }
  }
}
</script>
