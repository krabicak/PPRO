import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-tabs/src/vaadin-tabs.js';

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
 <vaadin-vertical-layout style="height: 100%; align-items: stretch; width: 100%;" id="verticalLayout">
  <vaadin-tabs id="tabNavigation" style="align-self: stretch; flex-grow: 0;" orientation="horizontal" selected="0"></vaadin-tabs>
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
