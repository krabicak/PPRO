import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';

class MainView extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                    width: 100%;
                }
            </style>
<vaadin-vertical-layout style="width: 100%; height: 100%;" id="vaadinVerticalLayout">
 <vaadin-vertical-layout style="height: 100%; align-items: stretch; width: 100%; margin: var(--lumo-space-m); padding: var(--lumo-space-m);" id="verticalLayout" theme="spacing">
  <h2>Vítejte v Registru vozidel!</h2>
  <p>Pro pohyb mezi nabídkami použijte menu v horní části obrazovky. Aplikace je velice intuitivní a umožňuje záznamy snadno filtrovat, vyhledávat či mazat.</p>
  <div id="usersManagement">
   <h3>Správa uživatelů</h3>
   <p>Ve formuláři Správa uživatelů spravujete uživatele, jejich údaje a nastavujete jim role.</p>
  </div>
  <div id="vehiclesManagement">
   <h3>Vozidla</h3>
   <p>Na kartě Vozidla spravujete vozidla v systému, údaje o nich a jejich stav.</p>
  </div>
  <div id="insuranceCompanyManagement">
   <h3>Pojišťovny</h3>
   <p>V tomto formuláři spravujete jednotlivé pojišťovny</p>
  </div>
  <div id="insuranceManagement">
   <h3>Pojištění</h3>
   <p>Karta pojištění slouží k pojištění jednotlivých vozidel u pojišťovny.</p>
  </div>
  <p style="align-self: flex-start;">Doufáme, že se Vám aplikace bude líbit, autoři</p>
 </vaadin-vertical-layout>
</vaadin-vertical-layout>
`;
    }

    static get is() {
        return 'main-view';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(MainView.is, MainView);
