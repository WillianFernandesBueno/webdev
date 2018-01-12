import { ComunicaBackendProvider } from '../../providers/comunica-servidor-provider';
import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';
import { GooglePlus } from '@ionic-native/google-plus';

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
  providers: [ComunicaBackendProvider,GooglePlus]
})
export class HomePage {

  constructor(public navCtrl: NavController, public comunicaBackendProvider: ComunicaBackendProvider, public googlePlus: GooglePlus) {
    this.login();
    
  }

  realizaComunicacao() {
    this.comunicaBackendProvider.comunicaViaPost(null, null, this.retornaSucesso);
  }


  retornaSucesso = () => {
    alert("sucesso");
  }

  login() {
    this.googlePlus.login({})
      .then(res => {
        console.log(res);
        // this.displayName = res.displayName;
        // this.email = res.email;
        // this.familyName = res.familyName;
        // this.givenName = res.givenName;
        // this.userId = res.userId;
        // this.imageUrl = res.imageUrl;

        // this.isLoggedIn = true;
        console.log("logou");
      })
      .catch(err => console.error(err));
  }

  logout() {
    this.googlePlus.logout()
      .then(res => {
        console.log(res);
        // this.displayName = "";
        // this.email = "";
        // this.familyName = "";
        // this.givenName = "";
        // this.userId = "";
        // this.imageUrl = "";

        // this.isLoggedIn = false;
      })
      .catch(err => console.error(err));
  }
}
