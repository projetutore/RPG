package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import jeu.Jeu;
import jobs.Heros;
import jobs.Monstre;
import Objets.Interface.Objet;
import carte.Carte;



public class FenetredeJeu extends JFrame {
	private Jeu jeu;
	private JPanel map;
	private JPanel panelInventaire;
	private Container fenetredejeu;
	private JProgressBar bar;
	private JProgressBar barxp;
	private Thread t;
	private Thread ty;
	//private JTextArea gestionInventaire;
	private JButton[] gestionInventaire;
	Heros myHeros;
	JList tableMonstreList;
	private JLabel pts; 
	private JLabel car[] = new JLabel[3];
	private JButton plus[] = new JButton[3];
	private JButton moins[] = new JButton[3];
	JButton mainGauche;
	JButton mainDroite;
	JTextArea identitePerso = new JTextArea();
	JButton valider = new JButton("Valider");
	JButton annuler = new JButton("Annuler");
	DefaultListModel<String> tableMonstre;
	private int[] stat;
	private int f, c, a = 0;
	public final static int FORCE = 0, CONSTITUTION = 1, AGILITE = 2;
	public static final int GAUCHE = 2, DROITE = 1;
	public static final String nomCol[] = { "Nom du monstre", "Point de vies" };

	private int proviPtsRestant;

	
	
	public static final Font Andalus = new Font("Andalus", Font.PLAIN, 16);
	public static final int FLECHE_HAUT = 38;
	
	public FenetredeJeu(String titre , int x , int y , int w , int h , Jeu j){
		super(titre);
		this.jeu = j;	
		myHeros = jeu.getPersonnageJoueur();
		proviPtsRestant = myHeros.ptsRestant;

		this.initialise();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(x, y, w , h);
		this.setVisible(true);
		t = new Thread(new Traitement());
		ty = new Thread(new GestionEquipement());
		ty.start();
	}
	
		
	public void initialise(){
		fenetredejeu = this.getContentPane();
		fenetredejeu.add(this.carteCentre(), BorderLayout.CENTER);
		fenetredejeu.add(this.panelOuest(), BorderLayout.WEST);
		bar.setString(jeu.getPersonnageJoueur().getPa()+"/"+jeu.getPersonnageJoueur().getMaxPa());
	}	
	

	public JPanel panelCentre(){
		JPanel centre = new JPanel();
		JPanel centreBas = new JPanel();
		JPanel centreReste = new JPanel();
		JTextArea affichageCarte = new JTextArea(jeu.getCarte().Afficher());
		affichageCarte.setRows(jeu.getCarte().getnbLig());
		affichageCarte.setColumns(jeu.getCarte().getnbCol());

		centreReste.add(affichageCarte);
		affichageCarte.setSize(400, 200);
		centre.add(centreBas, BorderLayout.SOUTH);
		centre.add(centreReste, BorderLayout.CENTER);
		centreBas.add(new JTextArea());
		return centre;
	}
	
	public JPanel carteCentre(){
		 map = new JPanel(new GridLayout(jeu.getCarte().getnbLig(),jeu.getCarte().getnbCol()));
		JButton[][] zone = new JButton[jeu.getCarte().getnbLig()][jeu.getCarte().getnbCol()];
		for(int i = 0; i <jeu.getCarte().getnbLig(); i++){
			for(int j = 0; j< jeu.getCarte().getnbCol(); j++){

				if (jeu.getCarte().getGrille()[i][j] instanceof Monstre){
					zone[i][j] = new JButton("M");
					zone[i][j].setBackground(Color.RED);
					zone[i][j].setForeground(Color.BLACK);			
					map.add(zone[i][j]);
	
				}
				else if (jeu.getCarte().getGrille()[i][j] instanceof Heros ){
					zone[i][j]= new JButton("H");
					zone[i][j].setBackground(Color.GREEN);

					map.add(zone[i][j]);
				}
			
				else if (jeu.getCarte().getGrille()[i][j] instanceof Objet ){
					zone[i][j] = new JButton("O");
					map.add(zone[i][j]);
					zone[i][j].setBackground(Color.YELLOW);
					}		
				else if(jeu.getCarte().getGrille()[i][j].toString().equals("#")){
						zone[i][j] = new JButton();
						map.add(zone[i][j]);
						zone[i][j].setBackground(Color.BLACK);
				}
				else{
					zone[i][j]= new JButton();
					map.add(zone[i][j]);
				}
				zone[i][j].setEnabled(false);
			}
			
		}
	
		return map;

	}
	
	public JPanel panelOuest(){
		jeuActionListener jl = new jeuActionListener();

		JPanel panelOuest = new JPanel(new BorderLayout());
		JTabbedPane onglet = new JTabbedPane();
		panelInventaire = this.gestionInventaireMenu();
		JPanel panelHeros = new JPanel(new BorderLayout());
		JPanel panelAttaque = new JPanel(new BorderLayout());
		Box panelNiveau = this.GestiondePoints();
		onglet.add("Heros", panelHeros);
		onglet.add("Inventaire", panelInventaire);
		onglet.add("Historique", panelAttaque);
		onglet.addTab("Gestion de points", panelNiveau);
		
		JPanel panelBouton = new JPanel(new GridLayout(3,3,0,0));
		JPanel commande = new JPanel(new BorderLayout());
		
		JTextArea vide = new JTextArea();
		PrintStream historique = new PrintStream(new TerminalArea(vide));
		panelAttaque.add(vide);
		System.setOut((historique));

		JPanel gestionAttaque = new JPanel(new BorderLayout());
		
		 mainGauche = new JButton("Main Gauche");
		 mainDroite = new JButton("Main Droite");

		gestionAttaque.add(mainGauche, BorderLayout.WEST);
		gestionAttaque.add(mainDroite, BorderLayout.EAST);
		
		mainGauche.addActionListener(new attaqueActionListener(GAUCHE));
		mainDroite.addActionListener(new attaqueActionListener(DROITE));
		JLabel titre = new JLabel("EXP");
		commande.add(gestionAttaque, BorderLayout.CENTER);
		commande.add(titre, BorderLayout.NORTH);
		bar = new JProgressBar();
		barxp= new JProgressBar();
		commande.add(bar, BorderLayout.EAST);
		bar.setMaximum(jeu.getPersonnageJoueur().getMaxPa());
		bar.setMinimum(0);
		bar.setStringPainted(true);
		commande.add(barxp, BorderLayout.WEST);

		JButton action[] = new JButton[9];
		for(int i = 0; i<9; i++){	
			switch(i){
			case 1:action[i] = new JButton("Haut");
			break;
			case 3:action[i] = new JButton("Gauche");
			break;
			case 5: action[i]= new JButton("Droite");
			break;
			case 7: action[i]= new JButton("Bas");
			break;
			default:action[i]=new JButton();
			action[i].setEnabled(false);
			}
			action[i].addActionListener(jl);
			panelBouton.add(action[i]);
		}
		JTextArea alpha = new JTextArea(jeu.getPersonnageJoueur().affichageCaracteristique());		
		alpha.setEditable(false);
		panelHeros.add(alpha, BorderLayout.NORTH);
		
		tableMonstre = new DefaultListModel<String>();
		tableMonstreList = new JList(tableMonstre);
		JScrollPane monstreScrollPane = new JScrollPane(tableMonstreList);
		
		panelHeros.add(tableMonstreList, BorderLayout.CENTER);

		panelOuest.add(onglet);
		commande.add(panelBouton, BorderLayout.SOUTH);

		panelOuest.add(commande, BorderLayout.SOUTH);
	
		return panelOuest;
	}

	public JPanel gestionInventaireMenu(){
		panelInventaire = new JPanel(new GridLayout(4,5, 0,0 ));
		gestionInventaire= new JButton[jeu.getPersonnageJoueur().getInventaire().length];
		for(int i = 0; i < gestionInventaire.length; i++){
			try{
				gestionInventaire[i] = new JButton();
				gestionInventaire[i].setText(myHeros.getInventaire()[i].getNomObjet());
				panelInventaire.add(gestionInventaire[i]);
			}catch(NullPointerException e){
				gestionInventaire[i].setEnabled(false);
				panelInventaire.add(gestionInventaire[i]);
			}
		}
		return panelInventaire;
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
	
	public Box GestiondePoints(){
				
		Box panelCentre = Box.createVerticalBox();
		Box panelForce = Box.createHorizontalBox();
		Box panelConstitution = Box.createHorizontalBox();
		Box panelAgilite = Box.createHorizontalBox();
		Box points = Box.createHorizontalBox();

		BoutonPlusListener ajouter = new BoutonPlusListener();
		BoutonMoinsListener retirer = new BoutonMoinsListener();
		
		car[0] = new JLabel(" For : " + myHeros.getForce()  + " (+" + f + ") " + "( "+ myHeros.getdForce() + ")");
		car[1] = new JLabel(" Cons : " + myHeros.getConstitution()  + " (+" + c + ") "  + "( "+ myHeros.getdConstitution() + ")");
		car[2] = new JLabel(" Agi : " + myHeros.getAgilite()  + " (+" + a + ") "  + "( "+ myHeros.getdAgilite() + ")");
		pts = new JLabel(myHeros.ptsRestant+"", SwingConstants.CENTER);
		Font Modern_No = new Font("Modern No. 20", Font.PLAIN, 14);
		pts.setText("Points à distribuer : " + myHeros.ptsRestant + " points");
		points.add(pts);
		panelCentre.add(points);
		
		
		for(int i = 0; i<3; i++){
			plus[i] = new JButton();
			moins[i] = new JButton();
			plus[i].setText("+");
			plus[i].setActionCommand("+"+i);
			plus[i].addActionListener(ajouter);
			plus[i].setEnabled(false);

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
		pts.setText("Points à distribuer : " + myHeros.ptsRestant + " points");
	}
	
	class PanelDiagonal extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int larg = this.getWidth()/jeu.getCarte().getnbCol();
			int haut = this.getHeight()/jeu.getCarte().getnbLig();
			g.drawRect(10, 10, larg, haut);
		}
	}
	
	class attaqueActionListener implements ActionListener{
		private int val;
		
		public attaqueActionListener(int i){
			val = i;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Carte c = jeu.getCarte();
			ArrayList<Monstre> monstre_a_attaquer = null;	
			String nomMonstre = null;
			
			int index = tableMonstreList.getSelectedIndex();
			
			if(index!=-1){
			switch(val){			
			case DROITE:
				for(int i = 0; i< c.getnbLig(); i++){
					for(int j = 0; j < c.getnbCol(); j++){
						nomMonstre =  tableMonstre.get(index);
						if(c.getGrille()[i][j] instanceof Monstre){
							Monstre x = (Monstre) c.getGrille()[i][j];
							if(x.getNom().equals(nomMonstre))
								myHeros.getMainDroite().attaquer(myHeros, (Monstre) c.getGrille()[i][j]);
							
						}
						
					}
					
				}
				break;	
			case GAUCHE:
				for(int i = 0; i< c.getnbLig(); i++){
					for(int j = 0; j < c.getnbCol(); j++){
						nomMonstre =  tableMonstre.get(index);
						if(c.getGrille()[i][j] instanceof Monstre){
							Monstre x = (Monstre) c.getGrille()[i][j];
							if(x.getNom().equals(nomMonstre))
								myHeros.getMainGauche().attaquer(myHeros, (Monstre) c.getGrille()[i][j]);
						}
					}
				}
				break;
			}

			

			
			
			}
			
		}
	}
	class jeuActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			if(evt.getActionCommand().equals("Haut")){
				jeu.getCarte().deplacerHaut(jeu.getPersonnageJoueur());
				panelInventaire = gestionInventaireMenu();
				fenetredejeu.add(carteCentre(), BorderLayout.CENTER);	
			}
			else if(evt.getActionCommand().equals("Bas")){
				jeu.getCarte().deplacerBas(jeu.getPersonnageJoueur());
				fenetredejeu.add(carteCentre(), BorderLayout.CENTER);	
			}
			else if(evt.getActionCommand().equals("Gauche")){
				jeu.getCarte().deplacerGauche(jeu.getPersonnageJoueur());
			}
			else if(evt.getActionCommand().equals("Droite")){
				jeu.getCarte().deplacerDroite(jeu.getPersonnageJoueur());
				}
			myHeros.translaterPa(-3);
			fenetredejeu.add(carteCentre(), BorderLayout.CENTER);	
		//	gestionInventaire.setText((jeu.getPersonnageJoueur().affichageInventaire()));
		
			Carte c = jeu.getCarte();
			ArrayList<Monstre> monstre_a_attaquer = null;	
			String nomMonstre[] = null;
			
			tableMonstre.clear();
			
			monstre_a_attaquer = c.attaquer(myHeros, DROITE);
			monstre_a_attaquer = c.attaquer(myHeros, GAUCHE);

		for(int i = 0; i < monstre_a_attaquer.size(); i++){
			nomMonstre = new String[monstre_a_attaquer.size()];
			nomMonstre[i] = monstre_a_attaquer.get(i).getNom();
			tableMonstre.addElement(nomMonstre[i]);
			System.out.println(monstre_a_attaquer);
		}

			panelInventaire = gestionInventaireMenu();
			panelInventaire.repaint();
			panelInventaire.revalidate();
			map.revalidate();	
			map.repaint();
			t.start();

		}	
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
			
			myHeros.ptsRestant  -= 1;
		if(myHeros.ptsRestant == 0){
			valider.setEnabled(true);
			for( JButton jbp : plus)
				jbp.setEnabled(false);
		}
		else{
			valider.setEnabled(false);
		}
		
		if(myHeros.ptsRestant != proviPtsRestant){
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
			if(myHeros.ptsRestant > proviPtsRestant-1){
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
				myHeros.ptsRestant= myHeros.ptsRestant+1;
			
			affichageStat();
			if(myHeros.ptsRestant>0)
				for(JButton jbp : plus)
					jbp.setEnabled(true);
		}
		
		
		public void annuler(){
			myHeros.ptsRestant = proviPtsRestant;
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

	class GestionEquipement implements Runnable{
		public void run(){
			int infini=-1;
			while(infini == -1){
				mainDroite.setText(myHeros.getMainDroite().getNomObjet());
				mainGauche.setText(myHeros.getMainGauche().getNomObjet());
				for(int i = 0; i< myHeros.getInventaire().length ; i++){
					try{
					gestionInventaire[i].setText(myHeros.getInventaire()[i].getNomObjet());
					gestionInventaire[i].setEnabled(true);

					}catch(NullPointerException e ){
						gestionInventaire[i].setEnabled(false);
					}
				}
			}
			try{
				t.sleep(10000000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	class Traitement implements Runnable{

		@Override
		public void run() {
			for(int val = 0; val <= jeu.getPersonnageJoueur().getPa(); val++){
				bar.setValue(val);
			}
			try{
				t.sleep(1000000000);
			}catch(InterruptedException e ){
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		Jeu y = new Jeu();
		FenetredeJeu encours = new FenetredeJeu("Abysse", 200, 500,1000,1000, y);
		
	}

}
