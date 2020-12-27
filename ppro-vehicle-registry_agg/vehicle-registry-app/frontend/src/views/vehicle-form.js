import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-button/src/vaadin-button.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-radio-button/src/vaadin-radio-group.js';
import '@vaadin/vaadin-radio-button/src/vaadin-radio-button.js';
import '@vaadin/vaadin-checkbox/src/vaadin-checkbox.js';
import '@polymer/iron-icon/iron-icon.js';
import '@vaadin/vaadin-grid/src/vaadin-grid.js';
import '@vaadin/vaadin-date-picker/src/vaadin-date-picker.js';
import '@vaadin/vaadin-text-field/src/vaadin-text-field.js';

class VehicleForm extends PolymerElement {

    static get template() {
        return html`
<style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                    width: 100%;
                }
            </style>
<vaadin-horizontal-layout class="content" style="width: 100%; height: 100%;" id="vaadinHorizontalLayoutUsers">
 <vaadin-vertical-layout theme="spacing" id="vaadinVerticalLayout" style="width: 80%; padding: var(--lumo-space-m);">
  <vaadin-text-field placeholder="Hledat" id="fieldSearch">
   <iron-icon icon="lumo:search" slot="prefix"></iron-icon>
  </vaadin-text-field>
  <vaadin-grid items="[[items]]" id="gridVehicles" style="flex-shrink: 0; width: 100%;"></vaadin-grid>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing" id="vaadinVerticalLayoutUsers" style="width: 20%; height: 100%; padding: var(--lumo-space-m);">
  <vaadin-text-field label="SPZ" placeholder="SPZ" id="fieldSpz"></vaadin-text-field>
  <vaadin-radio-group value="foo" id="radioRole">
   <vaadin-radio-button name="foo">
     Foo 
   </vaadin-radio-button>
   <vaadin-radio-button name="bar">
     Bar 
   </vaadin-radio-button>
   <vaadin-radio-button name="baz">
     Baz 
   </vaadin-radio-button>
  </vaadin-radio-group>
  <vaadin-text-field label="VIN" placeholder="VIN" id="fieldVin"></vaadin-text-field>
  <vaadin-date-picker label="Splatnost malého TP" placeholder="Vyber datum" id="dateSmallTechnical"></vaadin-date-picker>
  <vaadin-date-picker label="Splatnost velkého TP" placeholder="Vyber datum" id="dateBigTechnical"></vaadin-date-picker>
  <vaadin-text-field label="Jméno" placeholder="Jméno" id="fieldName"></vaadin-text-field>
  <vaadin-text-field label="Přijmení" placeholder="Přijmení" id="fieldSurname"></vaadin-text-field>
  <vaadin-checkbox id="checkBoxActive">
    Aktivní 
  </vaadin-checkbox>
  <vaadin-button id="buttonEditUser">
   <iron-icon icon="lumo:edit" slot="prefix"></iron-icon>Upravit 
  </vaadin-button>
  <vaadin-button id="ButtonDeleteUser">
   <iron-icon icon="lumo:cross" slot="prefix" id="ironIcon"></iron-icon>Smazat 
  </vaadin-button>
  <vaadin-button id="buttonAddVehicle" style="align-self: flex-start;">
   <iron-icon icon="lumo:plus" slot="prefix"></iron-icon>Přidat 
  </vaadin-button>
 </vaadin-vertical-layout>
</vaadin-horizontal-layout>
`;
    }

    static get is() {
        return 'vehicle-form';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(VehicleForm.is, VehicleForm);
