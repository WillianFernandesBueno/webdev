import { ComunicaBackendProvider } from '../../providers/comunica-servidor-provider';
import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
  providers: [ComunicaBackendProvider]
})
export class HomePage {

  constructor(public navCtrl: NavController, public comunicaBackendProvider: ComunicaBackendProvider) {
    
  }

  realizaComunicacao() {
    this.comunicaBackendProvider.comunicaViaPost(null, null, this.retornaSucesso);
  }


  retornaSucesso = () => {
    alert("sucesso");
  }

}
