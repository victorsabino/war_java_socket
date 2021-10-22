package view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PnlDados extends JPanel {

		
		private static PnlDados pnlDados;
		private List<LblDado> lstLblDadosAtaque = new ArrayList<LblDado>();
		private List<LblDado> lstLblDadosDefesa = new ArrayList<LblDado>();	
		
		private PnlDados() {			
			
			carregaPnlDados();
			configJPanel();
			
		}
		
		private void configJPanel() {
			setLayout(new FlowLayout(FlowLayout.LEFT, 3 ,0));
			setLocation(408, 650);
			setSize(210, 32);
			setOpaque(false);		
		}
		
		private void carregaPnlDados() {
			
			while(lstLblDadosAtaque.size() < 3) {
				LblDado dado = new LblDado('a', lstLblDadosAtaque.size());
				lstLblDadosAtaque.add(dado);
				add(dado);				
			}
			
			while(lstLblDadosDefesa.size() < 3) {
				LblDado dado = new LblDado('d', lstLblDadosDefesa.size());
				lstLblDadosDefesa.add(dado);
				add(dado);
			}
			
		}
		
		public static PnlDados getInstance(){
			
			if(pnlDados == null) {
				pnlDados = new PnlDados();
			}
			
			return pnlDados;
		}


}
