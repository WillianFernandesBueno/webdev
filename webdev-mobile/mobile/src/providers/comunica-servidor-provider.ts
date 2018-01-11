import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from "rxjs";
import { LoadingController, Loading } from 'ionic-angular';
import 'rxjs/add/operator/map';

declare var cordova: any;

@Injectable()
export class ComunicaBackendProvider {

  private _urlBaseSelecionada: number;
  public urlServico: String;
  public loadingAnimacao: Loading;
  private flagApresentaErro:Boolean;

  constructor(public http: Http, public loadingController: LoadingController) {
    this.init();
  }

  init() {
    this.loadingAnimacao = this.loadingController.create();
  }

  protected postBackEnd(url: String, dados: any, metodoAExecutarSucesso, metodoAExecutarErro, isBackground?: boolean) {
    var headers = new Headers();
    headers.append('Content-Type', 'application/json');
    let urlCompleta = this.defineUrlCompleta(url);
    let obs: Observable<any>;
    //if (dados instanceof DTO) {
    //  obs = this.http.post(urlCompleta, JSON.stringify(dados), { headers: headers }).map(res => res.json());
    //} else {
    obs = this.http.get(urlCompleta, { headers: headers }).map(res => res.json());
      // obs = this.http.post(urlCompleta, dados, { headers: headers }).map(res => res.json());
    //}
    this.handlePromise(obs, metodoAExecutarSucesso, metodoAExecutarErro, dados, isBackground);
  }

  protected handlePromise(resultado: Observable<any>, metodoAExecutarSucesso, metodoAExecutarErro, dados: any, isBackground: boolean) {
    resultado.subscribe(
      resposta => {
        console.log(resposta);
        this.escondeLoading(isBackground);
        if (resposta.status != 200 && resposta.status != 307) {

          if(this.flagApresentaErro == undefined || this.flagApresentaErro == null || this.flagApresentaErro === true){
	          this.exibeMensagensErro(resposta);
	          this.flagApresentaErro = null;
          }

          if (metodoAExecutarErro) {
            metodoAExecutarErro();
          }
        } else {
          //this.sessaoProvider.dataServidor = resposta.dataHoraSolicitacao;
          //this.sessaoProvider.$offsetServidor = resposta.timeZoneOffset;
          metodoAExecutarSucesso(resposta, dados);
            if(resposta.mensagens != null ) {
              for (let entry of resposta.mensagens) {
                if(entry.tipo == "ALERTA"){
                  //this.mensagensProvider.$mensagensBackend.push(new MensagemDTO(ListaTipoMensagens.ALERTA, entry.mensagem));
                }
                if(entry.tipo == "ERRO"){
                  //this.mensagensProvider.$mensagensBackend.push(new MensagemDTO(ListaTipoMensagens.ERRO, entry.mensagem));
                }
              }
            }
        }
      },
      err => {
        console.log("ERROR!: ", err);
        this.escondeLoading();
        if (metodoAExecutarErro) {
          metodoAExecutarErro();
        } else {
          //this.mensagensProvider.apresentaMensagemErro(ListaMensagens.FALHA_COMUNICACAO);
        }
      });
  }

  private defineUrlCompleta(url: String) {
    // if (!this.urlServico) {
    //   this.urlServico = this.urlServico ? this.urlServico : null;
    // }
    //  this.urlServico.concat(url.toString());
    
    return "https://172.25.1.64:8443/webdev/api/autenticacao/realizaLogin";
  }

 

  public exibeMensagensErro(resposta) {
    if (resposta.mensagens != null) {
      for (let mensagem of resposta.mensagens) {
        //this.mensagensProvider.apresentaMensagemErro(mensagem.mensagem);
      }
    }
  }

  public comunicaViaPost(servico: any, dados: any, metodoAExecutarSucesso, metodoAExecutarErro?, flagApresentaErro?:Boolean) {
    this.flagApresentaErro = flagApresentaErro;

    if (servico && !servico.background) {
      this.init();
      this.apresentaLoading();
    }

    this.postBackEnd(null, dados, metodoAExecutarSucesso, metodoAExecutarErro, null);
  }

  public apresentaLoading() {
    if (this.loadingAnimacao) {
      this.loadingAnimacao.present();
    }
  }

  private escondeLoading(isBackground?: boolean) {
    if (this.loadingAnimacao && !isBackground) {
      this.loadingAnimacao.dismiss();
    }
  }

  /*public get urlBaseSelecionada(): number {
    return this._urlBaseSelecionada;
  }*/


  /*public set urlBaseSelecionada(value: number) {
    this._urlBaseSelecionada = value;
    this.urlServico = ListaServidores.ambienteEspecifico(value).url + ConstantesConexaoBackend.nomeAplicacao;
    if (this.dispositivoProvider.isMobile()) {
      FotonAtualizaDefaults.atualizaDadoDefaults("urlBackend", this.urlServico);
    }
  }*/


}
