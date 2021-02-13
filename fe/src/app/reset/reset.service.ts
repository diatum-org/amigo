import { Injectable, Type, Component } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse, HttpEvent } from '@angular/common/http';
import { HttpUrlEncodingCodec } from '@angular/common/http';
import { sha256 } from 'js-sha256';

import { Contact } from './contact';

@Injectable()
export class ResetService {

  private headers: HttpHeaders;

  constructor(private httpClient: HttpClient) {
    this.headers = new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' });
  }

  public getReset(token: string): Promise<Contact> {
    return this.httpClient.get<Contact>("reset/account?token=" + token,
        { headers: this.headers, observe: 'body' }).toPromise();
  }

  public setReset(token: string, password: string): Promise<void> {
    var hash = sha256.create();
    hash.update("dikota:" + password);
    return this.httpClient.put<void>("reset/account?token=" + token + "&password=" + hash.hex(),
        { headers: this.headers, observe: 'body' }).toPromise();
  }

}


