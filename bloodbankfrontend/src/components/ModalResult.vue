<template>
  <div v-if="show" class="modal-mask">
    <div class="modal-container class-text">
      <header>
        <h3>
          Blood test results
        </h3>
      </header>
      
      <div>
        <Field  v-model="this.result" class="model form-control form-control-md" type="text" name="result" >
        </Field>
      </div>
      <br>
      <footer>
        <button @click="this.sendResults()" class="button-333" role="button">Send</button>
        <button @click="$emit('cancel')" class="button-33" role="button" type="submit">Cancel</button>
      </footer>
      
    </div>
  </div>
</template>

<script>
import { Field } from "vee-validate";
import AppointmentService from "../services/appointment.js";
export default {
  name: "ModalResult",
  components:{
    Field
  },
  props:{
    show: Boolean,
  },
  data() {
    return{
      message:"",
      result:""
    }
  },

  methods:{

    sendResults(){
      let id = JSON.parse(localStorage.getItem('appointmentId'));
      console.log(id);
      AppointmentService.sendBloodTestResults(id,this.result).then(
        () => {
          this.message = "Results sent!";
          this.$emit('cancel');
        }
      )
    }
  },

};
</script>


<style>
@import url("../assets/styles/cancel_button.css");
@import url("../assets/styles/submit_button.css");
.modal-mask {
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, .5);
  display:grid;
  place-items: center;
}
.modal-container{
  width: 50px;
  background:white;
  border-radius: 25px;
  padding:1rem;
  width: 75%;
  max-width: 500px;
}
.model{
  width: 100%;
  height: calc(1.5em + .75rem + 2px);
  border-radius: 5px;
  padding: 0.5rem;
  border: 1px solid #ced4da;
  font-size: 1rem;
  line-height: 1.5;
  color: #495057;
}
.class-text{
  color:black;
}
</style>