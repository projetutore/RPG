package jobs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

@SuppressWarnings("serial")
public class FichePerso extends JFrame{

	private Heros myHeros;
	private int ptsRestant;
	private int proviPtsRestant;
	private JLabel pts; 
	private JLabel car[] = new JLabel[5];
	private JButton plus[] = new JButton[5];
	private JButton moins[] = new JButton[5];
	JTextArea identitePerso = new JTextArea();
	JButton valider = new JButton("Valider");
	JButton annuler = new JButton("Annuler");
	private int[] stat;
	private int f, c, a, v = 0;
	public final static int FORCE = 0, CONSTITUTION = 1, AGILITE = 2;

	private Font Andalus = new Font("Andalus", Font.PLAIN, 16);
 
	
	public FichePerso (String titre, Heros myHeros, int ptsRestant){
		super(titre);
		Toolkit atk = Toolkit.getDefaultToolkit();
		Dimension dim = atk.getScreenSize();
		this.setSize(dim.width/2, dim.height-100);
		this.myHeros = myHeros;
		this.ptsRestant = ptsRestant;
		stat = myHeros.repartitionPoints();
		proviPtsRestant = ptsRestant;
		this.initialise();
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		}
	
	public void initialise(){
		Container fichePerso = this.getContentPane();
		fichePerso.add(this.panelCentre(), BorderLayout.CENTER);
		fichePerso.add(this.panelHautGauche(), BorderLayout.WEST);
		fichePerso.add(this.panelHaut(), BorderLayout.NORTH);
	}	
	
	public JPanel panelHautGauche(){
	/*	JPanel panelHautGauche = new JPanel();
		panelHautGauche.setLayout(new BoxLayout(panelHautGauche, BoxLayout.Y_AXIS));
		JPanel texte = new JPanel();
		texte.setLayout(new BoxLayout(texte, BoxLayout.LINE_AXIS));
		identitePerso.setSize(300,200);
		JPanel abc = new JPanel();
		abc.setLayout(new BoxLayout(abc, BoxLayout.X_AXIS));
		JLabel ab= new JLabel("Vie: ");
		JButton plus = new JButton("+");
		
		
		identitePerso.setFont(Andalus);
		texte.add(identitePerso);
		abc.add(ab);	
		panelHautGauche.add(texte);
		panelHautGauche.add(abc);
		return panelHautGauche;*/
		JPanel panelHautGauche = new JPanel();
		panelHautGauche.setLayout(new BoxLayout(panelHautGauche, BoxLayout.Y_AXIS));
		JPanel panelTexte = new JPanel();
		panelTexte.setLayout(new BoxLayout(panelTexte, BoxLayout.X_AXIS));
		JPanel panelVie = new JPanel();
		panelVie.setLayout(new BoxLayout(panelVie, BoxLayout.X_AXIS));
		JPanel panelMana = new JPanel();
		panelMana.setLayout(new BoxLayout(panelMana, BoxLayout.X_AXIS));
		JLabel vie = new JLabel(" Vie : " + this.myHeros.getMaxVie()  + " (+" + v + ") ");
		
		JButton viePLUS = new JButton("+");
		JButton vieMOINS = new JButton("-");

		identitePerso.setText("\nNom: "+ this.myHeros.getNom()+ "\nClasse: "
				+ this.myHeros.getJob()+"\nNiveau :" + this.myHeros.getNiveau() +"\nDegats:"+ this.myHeros.getDegats()+"\nAttaque:"
				+ this.myHeros.getAttaque()+ "\nPA:" +this.myHeros.getPa() + "/"+ this.myHeros.getMaxPa() 
				+"\nExp : " +this.myHeros.getExperience() + "/100");		
		identitePerso.setBackground(getBackground());
		identitePerso.setEditable(false);
		panelTexte.add(identitePerso);
			CompoundBorder personnage = BorderFactory.createCompoundBorder(
					BorderFactory.createTitledBorder("Feuille du Personnage"), 
					BorderFactory.createEmptyBorder(0,0,0,50));
			panelHautGauche.setBorder(personnage);

		panelVie.add(viePLUS);
		panelVie.add(vie);
		panelVie.add(vieMOINS);

		
		panelHautGauche.add(panelTexte);
		panelHautGauche.add(panelVie);
		panelHautGauche.add(panelMana);

		return panelHautGauche;
	}
	public JPanel panelHaut(){
		JPanel panelHaut = new JPanel();
		panelHaut.setLayout(new BorderLayout());
		CompoundBorder titre = BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Level UP"), 
				BorderFactory.createEmptyBorder(20,100,0,0));
		panelHaut.setBorder(titre);

		return panelHaut;
	}

	public Box panelBas(){
		Box panelBas = Box.createHorizontalBox();
		BoutonPlusListener ajouter = new BoutonPlusListener();
		BoutonMoinsListener retirer = new BoutonMoinsListener();
		valider.setEnabled(false);
		Dimension minSize = new Dimension(0, 20);
		Dimension prefSize = new Dimension(0, 20);
		Dimension maxSize = new Dimension(100, 20);
		valider.setPreferredSize(prefSize);
		valider.setMinimumSize(minSize);
		valider.setMaximumSize(maxSize);
		valider.addActionListener(ajouter);
		panelBas.add(valider);
	
		annuler.addActionListener(retirer);
		annuler.setPreferredSize(prefSize);
		annuler.setMinimumSize(minSize);
		annuler.setMaximumSize(maxSize);
		panelBas.add(annuler);
		return panelBas;
		
	}
	
	public Box panelCentre(){
		Box panelCentre = Box.createVerticalBox();
		Box panelForce = Box.createHorizontalBox();
		Box panelConstitution = Box.createHorizontalBox();
		Box panelAgilite = Box.createHorizontalBox();
		Box points = Box.createHorizontalBox();

		BoutonPlusListener ajouter = new BoutonPlusListener();
		BoutonMoinsListener retirer = new BoutonMoinsListener();
		car[0] = new JLabel(" For : " + this.myHeros.getForce()  + " (+" + f + ") " + "( "+ this.myHeros.getdForce() + ")");
		car[1] = new JLabel(" Cons : " + this.myHeros.getConstitution()  + " (+" + c + ") "  + "( "+ this.myHeros.getdConstitution() + ")");
		car[2] = new JLabel(" Agi : " + this.myHeros.getAgilite()  + " (+" + a + ") "  + "( "+ this.myHeros.getdAgilite() + ")");
		pts = new JLabel(ptsRestant+"", SwingConstants.CENTER);
		Font Modern_No = new Font("Modern No. 20", Font.PLAIN, 14);
		pts.setText("Points à distribuer : " + ptsRestant + " points");
		points.add(pts);
		panelCentre.add(points);
		
		
		for(int i = 0; i<5; i++){
			plus[i] = new JButton();
			moins[i] = new JButton();
			plus[i].setText("+");
			plus[i].setActionCommand("+"+i);
			plus[i].addActionListener(ajouter);


			moins[i].setText("-"); moins[i].setActionCommand("-"+i);
			moins[i].setEnabled(false);
			moins[i].addActionListener(retirer);

			
			switch(i){
			case 0:
				panelForce.add(plus[i]);
				panelForce.add(car[i]);
				panelForce.add(moins[i]);
				break;
			case 1:
				panelConstitution.add(plus[i]);
				panelConstitution.add(car[i]);
				panelConstitution.add(moins[i]);
				break;
			case 2:
				panelAgilite.add(plus[i]);
				panelAgilite.add(car[i]);
				panelAgilite.add(moins[i]);
				break;
			}
			
			
			plus[i].setFont(Andalus);
			moins[i].setFont(Andalus);
			car[i].setFont(Modern_No);
			
		//	panelIntelligence.add(pts);
		}
		
		panelCentre.add(panelForce);
		panelCentre.add(panelConstitution);
		panelCentre.add(panelAgilite);
		
		Dimension minSize = new Dimension(5, 40);
		Dimension prefSize = new Dimension(5, 40);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, 40);
		panelCentre.add(new Box.Filler(minSize, prefSize, maxSize));
		Box Valide = this.panelBas();
		panelCentre.add(Valide);

		return panelCentre;
	}
	
	public void affichageStat(){
		myHeros.remiseDegree();
		car[0].setText(" For : " + myHeros.getForce() + " (+" + f + ") "  + "( "+ this.myHeros.getdForce() + ")");
		car[1].setText(" Cons : " + myHeros.getConstitution()  + " (+" + c + ") "  + "( "+ this.myHeros.getdConstitution() + ")");
		car[2].setText(" Agi : " + myHeros.getAgilite()  + " (+" + a + ") "  + "( "+ this.myHeros.getdAgilite() + ")" );
		identitePerso.setText("\nNom: "+ this.myHeros.getNom()+ "\nClasse: "
				+ this.myHeros.getJob()+"\nNiveau :" + this.myHeros.getNiveau() +"\nDegats:"+ this.myHeros.getDegats()+"\nAttaque:"
				+ this.myHeros.getAttaque()+ "\nPA:" +this.myHeros.getPa() + "/"+ this.myHeros.getMaxPa() 
				+"\nExp : " +this.myHeros.getExperience() + "/100");	
		pts.setText("Points à distribuer : " + ptsRestant + " points");
	}
	
	public class BoutonPlusListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {
			
			String s = e.getActionCommand();
			if(s.equals("+0")){
				myHeros.monteeStat(FORCE);
				f = f+1;
			}
			else if(s.equals("+1")){
				myHeros.monteeStat(CONSTITUTION);
				c = c+1;
			}
			else if(s.equals("+2")){
				myHeros.monteeStat(AGILITE);
				a = a+1;
			}
			else if(s.equals("Valider")){
			      int option = JOptionPane.showConfirmDialog(null, 
			        "Etes-vous sur de la distribution de vos points?",
			        "Confirmation", 
			        JOptionPane.YES_NO_OPTION, 
			        JOptionPane.QUESTION_MESSAGE);

			      if(option != JOptionPane.NO_OPTION && 
			      option != JOptionPane.CLOSED_OPTION){
			    	  myHeros.remiseDegree();
			    	  dispose();

			      }
			    	  return;
			    }
			else{
				return;
			}
			
			ptsRestant= ptsRestant-1;
		if(ptsRestant == 0){
			valider.setEnabled(true);
			for( JButton jbp : plus)
				jbp.setEnabled(false);
		}
		else{
			valider.setEnabled(false);
		}
		
		if(ptsRestant != proviPtsRestant){
					if(f >0)
						moins[0].setEnabled(true);
					if(c>0)
						moins[1].setEnabled(true);
					if(a>0)
						moins[2].setEnabled(true);	
		}
		affichageStat();
		}
	}
	
	public class BoutonMoinsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			String s = e.getActionCommand();
			if(ptsRestant > proviPtsRestant-1){
				for( JButton jbm : moins )
					jbm.setEnabled(false);
			}
				
				
			if(s.equals("-0") && myHeros.getForce()>stat[0]){
				myHeros.baisseStat(FORCE);
				f = f-1;
			}
			else if(s.equals("-1") && myHeros.getConstitution()>stat[1]){
				myHeros.baisseStat(CONSTITUTION);
				c = c-1;
			}
			else if(s.equals("-2") && myHeros.getAgilite()>stat[2]){
				myHeros.baisseStat(AGILITE);
				a = a-1;
			}
			else if(s.equals("Annuler")){
				this.annuler();
			}
			else{
				return;
			}
			
			
			
			if(f==0)
				moins[0].setEnabled(false);
			if(c==0)
				moins[1].setEnabled(false);
			if(a==0)
				moins[2].setEnabled(false);
	
			
			if(!s.equals("Annuler"))
				ptsRestant= ptsRestant+1;
			
			affichageStat();
			if(ptsRestant>0)
				for(JButton jbp : plus)
					jbp.setEnabled(true);
		}
		
		
		public void annuler(){
			ptsRestant = proviPtsRestant;
			f =0; c=0; a=0;
			myHeros.setForce(stat[0]);
			myHeros.setConstitution(stat[1]);
			myHeros.setAgilite(stat[2]);
			affichageStat();
			for( JButton jbm : moins )
				jbm.setEnabled(false);
			for(JButton jba : plus)
				jba.setEnabled(true);
		}
	}

}
