import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-text-field/src/vaadin-password-field.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';

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
<vaadin-vertical-layout style="width: 100%; height: 100%; margin: var(--lumo-space-s); padding: var(--lumo-space-s);" id="vaadinVerticalLayout" theme="spacing-s">
 <vaadin-vertical-layout style="height: 100%; align-items: stretch; width: 100%;" id="verticalLayout">
  <h2 id="h2Greeting">Heading 2</h2>
  <p>Pro pohyb mezi nabídkami použijte menu v horní části obrazovky. Aplikace je velice intuitivní a umožňuje záznamy snadno filtrovat, vyhledávat či mazat.</p>
  <h2 style="align-self: flex-start;">Změna hesla</h2>
  <vaadin-password-field label="Heslo" placeholder="Zadejte heslo" id="fieldPassword1" has-value style="align-self: center;"></vaadin-password-field>
  <vaadin-password-field label="Heslo pro potvrzení" placeholder="Zadejte heslo znovu" id="fieldPassword2" has-value style="align-self: center;"></vaadin-password-field>
  <vaadin-button id="buttonPassword" style="align-self: center;">
    Změnit heslo 
  </vaadin-button>
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
