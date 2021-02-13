import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RootComponent } from './root/root.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: RootComponent 
  },
  {
    path: 'console',
    loadChildren: () => import('./console/console.module').then(mod => mod.ConsoleModule)
  },
  {
    path: 'confirm',
    loadChildren: () => import('./confirm/confirm.module').then(mod => mod.ConfirmModule)
  },
  {
    path: 'reset',
    loadChildren: () => import('./reset/reset.module').then(mod => mod.ResetModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
