package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;

import client.Client;
import client.SerializeData;
import model.*;

/**
 * @author Pedro
 *
 */
public class ControllerTabuleiro extends Observable {

	private static ControllerTabuleiro controller;
	private static boolean master = false;
	private static List<model.Exercito> lstJogadores = new ArrayList<model.Exercito>();
	private List<Jogada> lstJogadas = new ArrayList<Jogada>();
	private ArrayList<Continente> lstContinentes = new ArrayList<Continente>();
	private Iterator<model.Exercito> itJogador = null;
	private Iterator<Jogada> itJogada = getLstJogadas().iterator();
	public ArrayList<Dado> lstDadosAtaque = new ArrayList<Dado>();
	public ArrayList<Dado> lstDadosDefesa = new ArrayList<Dado>();
	private Exercito jogadorDaVez;
	private Deck deck;
	private Territorio territorioOrigem;
	private Territorio territorioDestino;
	private String mensagem;
	private int qtdTroca;
	private boolean conquistouTerritorio;
	private DeckObjetivos deckObjetivos;
	private Exercito vencedor;
	private static view.Exercito clientPlayer = null;
	private static view.Exercito viewMyself = null;
	private static Exercito myself = null;
	private static long seed = 0L;
	private static boolean seedSeted = false;
	// Bloco de inicialização das jogadas
	{
		lstJogadas = new ArrayList<Jogada>();
		lstJogadas.add(new Jogada("Distribuir"));
		lstJogadas.add(new Jogada("Atacar"));
		lstJogadas.add(new Jogada("Remanejar"));
	}

	// bloco de inicialização dos continentes
	{
		// Cores dos continentes
		Color corAmericaNorte = new Color(238, 64, 54);
		Color corAmericaSul = new Color(0, 104, 56);
		Color corAfrica = new Color(101, 45, 144);
		Color corAsia = new Color(246, 146, 30);
		Color corEuropa = new Color(43, 56, 143);
		Color corOceania = new Color(38, 169, 224);

		lstContinentes.add(new Continente("América do norte", corAmericaNorte, 5));
		lstContinentes.add(new Continente("América do sul", corAmericaSul, 2));
		lstContinentes.add(new Continente("África", corAfrica, 3));
		lstContinentes.add(new Continente("Ásia", corAsia, 7));
		lstContinentes.add(new Continente("Europa", corEuropa, 5));
		lstContinentes.add(new Continente("Oceania", corOceania, 2));

		carregaTerritorios();
	}

	// Bloco de inicialização dos dados
	{
		while (lstDadosAtaque.size() < 3) {
			lstDadosAtaque.add(new Dado('a'));
		}

		while (lstDadosDefesa.size() < 3) {
			lstDadosDefesa.add(new Dado('d'));
		}
	}

	private ControllerTabuleiro() {
		
	}
	


	public int getQtdTroca() {
		return qtdTroca;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
		notificaMudancas();
		this.mensagem = null;
	}

	private void carregaTerritorios() {
		Territorio Alasca = new Territorio("Alasca",
				new Ponto[] { new Ponto(86.1, 124.1), new Ponto(134.6, 124.1), new Ponto(111.4, 167.3),
						new Ponto(58.7, 167.3), new Ponto(72.3, 141.8), new Ponto(77.3, 141.8) },
				"an_alasca", new Ponto(86, 131), 3);

		Territorio Calgary = new Territorio("Calgary",
				new Ponto[] { new Ponto(148.7, 124.1), new Ponto(134.6, 124.1), new Ponto(126.4, 139.5),
						new Ponto(146.9, 177.0), new Ponto(223.0, 177.0), new Ponto(231.3, 163.2),
						new Ponto(245.5, 163.2), new Ponto(258.5, 139.5), new Ponto(284.1, 139.5),
						new Ponto(271.6, 116.9), new Ponto(241.7, 116.9), new Ponto(230.4, 137.8),
						new Ponto(212.9, 137.8), new Ponto(208.7, 130.1), new Ponto(152.4, 130.1) },
				"an_calgary", new Ponto(174, 141), 1);

		Territorio Groelandia = new Territorio("Groelandia",
				new Ponto[] { new Ponto(285.4, 94.6), new Ponto(271.6, 116.9), new Ponto(284.1, 139.5),
						new Ponto(328.7, 139.5), new Ponto(335.1, 149.5), new Ponto(350.8, 149.5),
						new Ponto(365.9, 122.1), new Ponto(371.4, 122.1), new Ponto(380.4, 108.5),
						new Ponto(371.0, 94.6) },
				"an_groelandia", new Ponto(316, 103), 1);

		Territorio Vancouver = new Territorio("Vancouver",
				new Ponto[] { new Ponto(111.4, 167.3), new Ponto(119.3, 182.3), new Ponto(102.1, 211.9),
						new Ponto(107.5, 220.0), new Ponto(214.0, 220.0), new Ponto(231.7, 191.2),
						new Ponto(223.0, 177.0), new Ponto(146.90, 177.0), new Ponto(126.4, 139.5) },
				"an_vancouver", new Ponto(156, 185), 3);

		Territorio Quebec = new Territorio("Quebec",
				new Ponto[] { new Ponto(262.2, 190.8), new Ponto(231.7, 190.8), new Ponto(214.0, 220.0),
						new Ponto(296.9, 220.0), new Ponto(304.3, 207.1), new Ponto(316.1, 207.1),
						new Ponto(312.5, 215.3), new Ponto(325.1, 215.3), new Ponto(329.9, 204.5),
						new Ponto(322.8, 191.4), new Ponto(331.6, 177.8), new Ponto(337.1, 184.7),
						new Ponto(344.7, 172.2), new Ponto(340.5, 163.4), new Ponto(321.2, 163.4),
						new Ponto(318.8, 160.4), new Ponto(288.7, 160.4), new Ponto(281.9, 171.9),
						new Ponto(272.9, 171.9) },
				"an_quebec", new Ponto(282, 177), 1);

		Territorio California = new Territorio("California",
				new Ponto[] { new Ponto(107.5, 220.0), new Ponto(84.7, 258.0), new Ponto(89.9, 268.9),
						new Ponto(78.2, 289.0), new Ponto(90.4, 310.2), new Ponto(126.4, 310.2),
						new Ponto(178.5, 220.0) },
				"an_california", new Ponto(110, 246), 2);

		Territorio Texas = new Territorio("Texas",
				new Ponto[] { new Ponto(202.2, 248.2), new Ponto(231.7, 248.2), new Ponto(246.1, 220.0),
						new Ponto(178.5, 220.0), new Ponto(126.4, 310.2), new Ponto(145.9, 343.8) },
				"an_texas", new Ponto(164, 243), 3);

		Territorio Nova_York = new Territorio("NovaYork",
				new Ponto[] { new Ponto(181.8, 310.2), new Ponto(165.7, 310.2), new Ponto(202.2, 248.2),
						new Ponto(231.7, 248.2), new Ponto(246.1, 220.0), new Ponto(296.9, 220.0),
						new Ponto(290.7, 231.7), new Ponto(279.4, 231.7), new Ponto(261.5, 265.1),
						new Ponto(243.7, 265.1), new Ponto(237.8, 279.1), new Ponto(231.3, 279.1),
						new Ponto(215.3, 306.5), new Ponto(207.3, 306.5), new Ponto(200.6, 318.9),
						new Ponto(211.5, 337.8), new Ponto(204.3, 349.4) },
				"an_novayork", new Ponto(201, 259), 2);

		Territorio Mexico = new Territorio("Mexico",
				new Ponto[] { new Ponto(90.4, 310.2), new Ponto(83.1, 323.0), new Ponto(103.6, 359.2),
						new Ponto(110.6, 346.5), new Ponto(102.5, 330.6), new Ponto(105.7, 324.8),
						new Ponto(136.6, 377.6), new Ponto(145.7, 377.6), new Ponto(162.1, 408.2),
						new Ponto(172.2, 408.2), new Ponto(179.8, 421.9), new Ponto(188.8, 406.2),
						new Ponto(183.8, 398.0), new Ponto(178.5, 398.0), new Ponto(173.0, 389.4),
						new Ponto(173.0, 386.3), new Ponto(175.3, 384.3), new Ponto(167.1, 370.0),
						new Ponto(171.8, 360.7), new Ponto(164.3, 348.1), new Ponto(161.4, 355.3),
						new Ponto(152.3, 355.3), new Ponto(126.4, 310.2) },
				"an_mexico", new Ponto(131, 346), 2);

		Territorio Venezuela = new Territorio("Venezuela",
				new Ponto[] { new Ponto(193.5, 398.2), new Ponto(254.3, 398.2), new Ponto(196.8, 498.1),
						new Ponto(188.8, 482.2), new Ponto(176.2, 482.2), new Ponto(161.2, 455.8),
						new Ponto(179.8, 421.9), new Ponto(188.8, 406.2) },
				"asl_venezuela", new Ponto(190, 423), 3);

		Territorio Peru = new Territorio("Peru",
				new Ponto[] { new Ponto(196.8, 498.1), new Ponto(202.3, 507.9), new Ponto(216.1, 507.9),
						new Ponto(232.2, 538.5), new Ponto(226.8, 545.4), new Ponto(239.7, 564.8),
						new Ponto(274.9, 505.0), new Ponto(233.8, 433.8) },
				"asl_peru", new Ponto(228, 485), 3);

		Territorio Brasil = new Territorio("Brasil",
				new Ponto[] { new Ponto(259.6, 407.2), new Ponto(285.3, 407.2), new Ponto(293.4, 423.9),
						new Ponto(305.7, 423.9), new Ponto(316.1, 445.2), new Ponto(335.4, 445.2),
						new Ponto(343.3, 461.0), new Ponto(343.3, 463.1), new Ponto(325.9, 491.3),
						new Ponto(333.8, 505.0), new Ponto(274.9, 505.0), new Ponto(233.8, 433.8),
						new Ponto(254.3, 398.2) },
				"asl_brasil", new Ponto(279, 444), 1);

		Territorio Argentina = new Territorio("Argentina",
				new Ponto[] { new Ponto(333.8, 505.0), new Ponto(302.4, 558.8), new Ponto(311.2, 573.0),
						new Ponto(281.6, 624.5), new Ponto(295.7, 650.2), new Ponto(278.1, 650.2),
						new Ponto(235.0, 572.4), new Ponto(239.7, 564.8), new Ponto(274.9, 505.0) },
				"asl_argentina", new Ponto(268, 553), 2);

		Territorio Africa_do_Sul = new Territorio("Africa do Sul",
				new Ponto[] { new Ponto(528.4, 545.4), new Ponto(558.1, 600.2), new Ponto(597.1, 600.2),
						new Ponto(606.1, 584.7), new Ponto(613.1, 584.7), new Ponto(626.6, 559.1),
						new Ponto(617.6, 545.4) },
				"af_africadosul", new Ponto(567, 557), 3);

		Territorio Angola = new Territorio("Angola",
				new Ponto[] { new Ponto(514.7, 493.2), new Ponto(519.9, 482.5), new Ponto(588.6, 482.5),
						new Ponto(599.4, 502.3), new Ponto(573.4, 545.4), new Ponto(528.4, 545.4),
						new Ponto(535.6, 531.4) },
				"af_angola", new Ponto(548, 499), 2);

		Territorio Argelia = new Territorio("Argélia",
				new Ponto[] { new Ponto(436.7, 337.5), new Ponto(473.2, 337.5), new Ponto(479.6, 350.3),
						new Ponto(492.5, 350.3), new Ponto(499.1, 364.1), new Ponto(539.8, 364.1),
						new Ponto(513.0, 410.1), new Ponto(425.3, 410.1), new Ponto(409.3, 384.5) },
				"af_argelia", new Ponto(460, 368), 1);

		Territorio Egito = new Territorio("Egito",
				new Ponto[] { new Ponto(588.6, 360.3), new Ponto(542.0, 360.3), new Ponto(539.8, 364.1),
						new Ponto(513.0, 410.1), new Ponto(547.6, 410.1), new Ponto(562.4, 436.6),
						new Ponto(623.6, 436.6), new Ponto(594.0, 385.3), new Ponto(597.8, 378.5) },
				"af_egito", new Ponto(561, 386), 3);

		Territorio Nigeria = new Territorio("Nigéria",
				new Ponto[] { new Ponto(425.3, 410.1), new Ponto(448.1, 452.2), new Ponto(503.3, 452.2),
						new Ponto(519.9, 482.5), new Ponto(588.6, 482.5), new Ponto(562.4, 436.6),
						new Ponto(547.6, 410.1) },
				"af_nigeria", new Ponto(519, 430), 1);

		Territorio Somalia = new Territorio("Somália",
				new Ponto[] { new Ponto(643.1, 502.0), new Ponto(653.0, 502.0), new Ponto(672.7, 464.6),
						new Ponto(639.3, 464.6), new Ponto(623.6, 436.6), new Ponto(562.4, 436.6),
						new Ponto(599.4, 502.3), new Ponto(573.4, 545.4), new Ponto(617.6, 545.4) },
				"af_somalia", new Ponto(608, 471), 2);

		Territorio Espanha = new Territorio("Espanha",
				new Ponto[] { new Ponto(442.3, 266.0), new Ponto(461.0, 266.0), new Ponto(476.9, 292.5),
						new Ponto(475.0, 296.6), new Ponto(480.0, 296.6), new Ponto(470.7, 312.3),
						new Ponto(452.3, 312.3), new Ponto(456.4, 304.0), new Ponto(443.2, 304.0),
						new Ponto(440.2, 309.2), new Ponto(415.9, 309.2) },
				"eu_espanha", new Ponto(441, 272), 1);

		Territorio Franca = new Territorio("França",
				new Ponto[] { new Ponto(526.8, 186.6), new Ponto(538.7, 210.4), new Ponto(508.1, 263.1),
						new Ponto(502.1, 263.1), new Ponto(494.4, 279.1), new Ponto(483.7, 279.1),
						new Ponto(476.9, 292.5), new Ponto(461.0, 266.0), new Ponto(466.7, 255.2),
						new Ponto(459.9, 244.7), new Ponto(452.3, 244.7), new Ponto(448.2, 235.7),
						new Ponto(488.6, 235.7), new Ponto(499.3, 219.1), new Ponto(516.2, 219.1),
						new Ponto(521.8, 207.7), new Ponto(517.8, 200.4) },
				"eu_franca", new Ponto(476, 238), 3);

		Territorio Italia = new Territorio("Itália", new Ponto[] { new Ponto(552.4, 210.4), new Ponto(538.7, 210.4),
				new Ponto(508.1, 263.1), new Ponto(516.6, 263.1), new Ponto(526.7, 281.8), new Ponto(533.7, 281.8),
				new Ponto(542.5, 298.2), new Ponto(542.5, 300.2), new Ponto(537.1, 308.2), new Ponto(549.2, 308.2),
				new Ponto(554.8, 297.3), new Ponto(556.1, 297.3), new Ponto(557.4, 299.5), new Ponto(563.9, 299.5),
				new Ponto(563.9, 297.7), new Ponto(552.8, 277.8), new Ponto(547.0, 277.8), new Ponto(539.1, 261.1),
				new Ponto(543.5, 253.4), new Ponto(552.8, 253.4), new Ponto(555.8, 259.4), new Ponto(567.5, 237.9) },
				"eu_italia", new Ponto(534, 222), 2);

		Territorio Polonia = new Territorio("Polônia",
				new Ponto[] { new Ponto(575.6, 176.1), new Ponto(583.6, 176.0), new Ponto(600.8, 205.9),
						new Ponto(581.8, 237.9), new Ponto(567.5, 237.9), new Ponto(552.4, 210.4),
						new Ponto(564.1, 189.0), new Ponto(569.8, 189.0) },
				"eu_polonia", new Ponto(566, 193), 3);

		Territorio Reino_Unido = new Territorio("Reino Unido",
				new Ponto[] { new Ponto(462.0, 146.2), new Ponto(481.6, 146.2), new Ponto(473.7, 156.6),
						new Ponto(480.8, 156.6), new Ponto(470.3, 174.2), new Ponto(482.7, 198.4),
						new Ponto(490.8, 198.4), new Ponto(484.0, 210.4), new Ponto(445.5, 210.4),
						new Ponto(451.3, 196.1), new Ponto(458.1, 196.1), new Ponto(463.2, 188.5),
						new Ponto(454.0, 174.2), new Ponto(459.4, 165.3), new Ponto(459.4, 163.2),
						new Ponto(450.8, 163.2) },
				"eu_reinounido", new Ponto(456, 169), 1);

		Territorio Romenia = new Territorio("Romênia",
				new Ponto[] { new Ponto(567.5, 237.9), new Ponto(555.8, 259.4), new Ponto(555.8, 262.4),
						new Ponto(557.6, 264.2), new Ponto(566.1, 264.2), new Ponto(579.8, 288.6),
						new Ponto(574.4, 297.5), new Ponto(581.4, 308.2), new Ponto(593.2, 308.2),
						new Ponto(601.4, 297.4), new Ponto(595.1, 289.5), new Ponto(598.3, 283.7),
						new Ponto(607.9, 283.7), new Ponto(581.8, 237.9) },
				"eu_romenia", new Ponto(571, 249), 3);

		Territorio Suecia = new Territorio("Suécia", new Ponto[] { new Ponto(551.0, 96.6), new Ponto(582.5, 96.6),
				new Ponto(610.2, 144.5), new Ponto(602.0, 158.0), new Ponto(571.5, 158.0), new Ponto(578.7, 145.7),
				new Ponto(569.6, 145.7), new Ponto(579.6, 127.8), new Ponto(562.9, 127.8), new Ponto(552.1, 145.8),
				new Ponto(558.1, 156.3), new Ponto(552.0, 167.3), new Ponto(555.4, 172.1), new Ponto(548.3, 183.7),
				new Ponto(535.8, 183.7), new Ponto(530.4, 171.1), new Ponto(521.7, 171.1), new Ponto(514.0, 183.7),
				new Ponto(501.4, 183.7), new Ponto(492.1, 166.6), new Ponto(507.0, 140.6), new Ponto(522.0, 140.6),
				new Ponto(537.1, 111.5), new Ponto(543.0, 111.5) }, "eu_suecia", new Ponto(520, 139), 2);

		Territorio Ucrania = new Territorio("Ucrânia",
				new Ponto[] { new Ponto(611.6, 254.4), new Ponto(620.1, 239.5), new Ponto(600.8, 205.9),
						new Ponto(581.8, 237.9), new Ponto(607.9, 283.7), new Ponto(619.0, 264.9) },
				"eu_ucrania", new Ponto(591, 227), 1);

		Territorio Arabia_Saudita = new Territorio("Arábia Saudita",
				new Ponto[] { new Ponto(646.2, 423.6), new Ponto(639.1, 434.7), new Ponto(649.1, 450.3),
						new Ponto(699.0, 450.3), new Ponto(726.6, 403.0), new Ponto(716.8, 387.3),
						new Ponto(680.5, 387.3), new Ponto(654.0, 342.4), new Ponto(627.4, 388.2) },
				"as_arabiasaudita", new Ponto(668, 398), 1);

		Territorio Bangladesh = new Territorio("Bangladesh",
				new Ponto[] { new Ponto(885.3, 350.0), new Ponto(847.8, 350.0), new Ponto(828.7, 383.5),
						new Ponto(842.1, 410.9), new Ponto(848.1, 410.9), new Ponto(859.7, 432.6),
						new Ponto(854.7, 441.3), new Ponto(870.5, 470.8), new Ponto(879.3, 458.8),
						new Ponto(879.3, 456.0), new Ponto(872.2, 444.6), new Ponto(879.9, 432.5),
						new Ponto(859.2, 396.3) },
				"as_bangladesh", new Ponto(838, 368), 1);

		Territorio Cazaquistao = new Territorio("Cazaquistão",
				new Ponto[] { new Ponto(907.9, 201.9), new Ponto(920.4, 222.6), new Ponto(906.1, 246.4),
						new Ponto(784.1, 246.4), new Ponto(772.1, 224.1), new Ponto(724.7, 224.1),
						new Ponto(739.0, 201.9) },
				"as_cazaquistao", new Ponto(819, 210), 1);

		Territorio Mongolia = new Territorio("Mongólia",
				new Ponto[] { new Ponto(906.1, 246.4), new Ponto(804.9, 246.4), new Ponto(822.0, 278.5),
						new Ponto(873.9, 278.5), new Ponto(894.6, 278.5), new Ponto(906.1, 300.4),
						new Ponto(916.3, 287.4), new Ponto(902.8, 264.5), new Ponto(910.3, 254.7) },
				"as_mongolia", new Ponto(847, 245), 3);

		Territorio China = new Territorio("China",
				new Ponto[] { new Ponto(873.9, 278.5), new Ponto(822.0, 278.5), new Ponto(804.9, 246.5),
						new Ponto(784.1, 246.5), new Ponto(773.5, 264.0), new Ponto(764.7, 278.9),
						new Ponto(754.8, 297.2), new Ponto(785.5, 348.8), new Ponto(785.5, 350.5),
						new Ponto(811.9, 350.5), new Ponto(838.9, 302.5), new Ponto(887.9, 302.5) },
				"as_china", new Ponto(787, 289), 2);

		Territorio Coreia_do_Norte = new Territorio("Coréia do Norte",
				new Ponto[] { new Ponto(839.0, 302.2), new Ponto(825.3, 326.1), new Ponto(914.9, 326.1),
						new Ponto(908.2, 314.2), new Ponto(894.1, 314.2), new Ponto(888.0, 302.2) },
				"as_coreiadonorte", new Ponto(857, 298), 2);

		Territorio Coreia_do_Sul = new Territorio(
				"Coréia do Sul", new Ponto[] { new Ponto(914.9, 326.1), new Ponto(922.1, 337.2),
						new Ponto(915.5, 350.0), new Ponto(811.9, 350.0), new Ponto(825.3, 326.1) },
				"as_coreiadosul", new Ponto(884, 323), 3);

		Territorio Estonia = new Territorio("Estônia",
				new Ponto[] { new Ponto(735.3, 123.8), new Ponto(659.5, 123.8), new Ponto(650.1, 144.1),
						new Ponto(630.0, 144.1), new Ponto(614.8, 117.0), new Ponto(628.2, 117.0),
						new Ponto(632.0, 124.2), new Ponto(644.4, 124.2), new Ponto(632.6, 101.5),
						new Ponto(607.8, 101.5), new Ponto(604.9, 96.6), new Ponto(582.5, 96.6),
						new Ponto(626.8, 173.1), new Ponto(706.8, 173.1) },
				"as_estonia", new Ponto(668, 135), 1);

		Territorio India = new Territorio("Índia",
				new Ponto[] { new Ponto(798.0, 450.3), new Ponto(763.8, 387.8), new Ponto(785.5, 351.2),
						new Ponto(785.5, 350.0), new Ponto(847.8, 350.0), new Ponto(808.0, 420.0),
						new Ponto(812.3, 427.5) },
				"as_india", new Ponto(792, 376), 3);

		Territorio Ira = new Territorio("Irã",
				new Ponto[] { new Ponto(716.8, 310.2), new Ponto(701.1, 310.2), new Ponto(691.0, 329.0),
						new Ponto(703.0, 351.2), new Ponto(716.5, 375.9), new Ponto(733.6, 375.9),
						new Ponto(739.9, 387.8), new Ponto(751.3, 370.1) },
				"as_ira", new Ponto(710, 334), 2);

		Territorio Iraque = new Territorio("Iraque",
				new Ponto[] { new Ponto(703.0, 351.2), new Ponto(694.0, 362.7), new Ponto(694.0, 364.8),
						new Ponto(706.5, 387.3), new Ponto(680.5, 387.3), new Ponto(654.0, 342.4),
						new Ponto(671.9, 310.2), new Ponto(701.1, 310.2), new Ponto(691.0, 329.0) },
				"as_iraque", new Ponto(671, 336), 3);

		Territorio Japao = new Territorio("Japão",
				new Ponto[] { new Ponto(937.3, 222.4), new Ponto(956.3, 254.3), new Ponto(953.7, 257.5),
						new Ponto(965.5, 276.8), new Ponto(955.4, 293.3), new Ponto(947.6, 293.3),
						new Ponto(940.0, 307.4), new Ponto(921.9, 307.4), new Ponto(930.2, 293.0),
						new Ponto(927.9, 288.6), new Ponto(933.8, 279.7), new Ponto(939.4, 279.7),
						new Ponto(943.7, 269.9), new Ponto(927.1, 239.4) },
				"as_japao", new Ponto(941, 258), 1);

		Territorio Jordania = new Territorio("Jordânia",
				new Ponto[] { new Ponto(621.9, 378.5), new Ponto(612.8, 378.5), new Ponto(602.1, 357.1),
						new Ponto(615.7, 337.6), new Ponto(633.6, 337.6), new Ponto(649.1, 310.2),
						new Ponto(671.9, 310.2), new Ponto(654.0, 342.4), new Ponto(627.4, 388.2) },
				"as_jordania", new Ponto(617, 342), 2);

		Territorio Letonia = new Territorio("Letônia",
				new Ponto[] { new Ponto(610.2, 144.5), new Ponto(591.0, 176.0), new Ponto(583.6, 176.0),
						new Ponto(611.2, 224.1), new Ponto(724.7, 224.1), new Ponto(739.0, 201.9),
						new Ponto(724.7, 173.1), new Ponto(626.8, 173.1) },
				"as_letonia", new Ponto(664, 186), 2);

		Territorio Paquistao = new Territorio("Paquistão",
				new Ponto[] { new Ponto(763.8, 387.8), new Ponto(739.9, 387.8), new Ponto(751.3, 370.1),
						new Ponto(708.9, 296.4), new Ponto(718.3, 278.9), new Ponto(764.7, 278.9),
						new Ponto(754.8, 297.2), new Ponto(785.5, 348.8), new Ponto(785.5, 351.2) },
				"as_paquistao", new Ponto(744, 317), 1);

		Territorio Russia = new Territorio("Russia",
				new Ponto[] { new Ponto(744.4, 125.0), new Ponto(742.5, 129.9), new Ponto(748.2, 129.9),
						new Ponto(744.1, 136.7), new Ponto(728.4, 136.7), new Ponto(706.7, 173.1),
						new Ponto(724.7, 173.1), new Ponto(739.0, 201.9), new Ponto(825.0, 201.9),
						new Ponto(869.9, 125.0) },
				"as_russia", new Ponto(778, 146), 3);

		Territorio Siberia = new Territorio("Sibéria",
				new Ponto[] { new Ponto(949.4, 204.5), new Ponto(956.8, 191.1), new Ponto(940.9, 162.5),
						new Ponto(947.1, 155.2), new Ponto(940.9, 144.4), new Ponto(954.0, 144.4),
						new Ponto(937.8, 117.0), new Ponto(875.3, 117.0), new Ponto(870.6, 124.9),
						new Ponto(825.0, 201.9), new Ponto(888.4, 201.9), new Ponto(877.3, 187.8),
						new Ponto(885.4, 176.1), new Ponto(907.5, 176.1), new Ponto(916.3, 159.9),
						new Ponto(930.5, 183.1), new Ponto(938.0, 183.1) },
				"as_siberia", new Ponto(882, 136), 2);

		Territorio Siria = new Territorio("Siria",
				new Ponto[] { new Ponto(660.8, 272.8), new Ponto(664.6, 278.9), new Ponto(718.3, 278.9),
						new Ponto(708.9, 296.4), new Ponto(716.8, 310.2), new Ponto(637.0, 310.2),
						new Ponto(637.0, 306.2), new Ponto(628.7, 306.6), new Ponto(619.6, 291.5),
						new Ponto(628.7, 275.8), new Ponto(646.1, 275.8), new Ponto(647.3, 272.8) },
				"as_siria", new Ponto(665, 280), 2);

		Territorio Tailandia = new Territorio("Tailândia",
				new Ponto[] { new Ponto(915.5, 350.0), new Ponto(885.3, 350.0), new Ponto(859.2, 396.3),
						new Ponto(879.9, 432.5), new Ponto(887.3, 446.2), new Ponto(895.1, 446.2),
						new Ponto(901.8, 432.5), new Ponto(897.5, 426.0), new Ponto(901.8, 420.7),
						new Ponto(887.0, 398.1), new Ponto(892.9, 389.8), new Ponto(895.9, 395.1),
						new Ponto(908.7, 395.1), new Ponto(911.4, 387.6), new Ponto(919.1, 387.6),
						new Ponto(927.4, 370.3) },
				"as_tailandia", new Ponto(888, 356), 3);

		Territorio Turquia = new Territorio("Turquia",
				new Ponto[] { new Ponto(683.6, 278.9), new Ponto(691.0, 268.9), new Ponto(678.5, 247.4),
						new Ponto(668.1, 247.4), new Ponto(664.5, 240.9), new Ponto(651.9, 240.9),
						new Ponto(646.3, 251.2), new Ponto(639.4, 239.5), new Ponto(620.1, 239.5),
						new Ponto(611.2, 224.1), new Ponto(772.1, 224.1), new Ponto(784.1, 246.4),
						new Ponto(764.7, 278.9) },
				"as_turquia", new Ponto(717, 237), 3);

		Territorio Australia = new Territorio("Autrália",
				new Ponto[] { new Ponto(875.1, 539.4), new Ponto(885.9, 539.4), new Ponto(919.7, 598.0),
						new Ponto(911.5, 611.8), new Ponto(917.6, 623.4), new Ponto(901.8, 650.0),
						new Ponto(891.1, 650.0), new Ponto(877.6, 673.5), new Ponto(846.1, 673.5),
						new Ponto(837.9, 655.8), new Ponto(823.4, 655.8), new Ponto(816.7, 641.9) },
				"oc_australia", new Ponto(866, 603), 3);

		Territorio Indonesia = new Territorio("Indonésia",
				new Ponto[] { new Ponto(850.9, 480.7), new Ponto(861.3, 500.4), new Ponto(880.7, 500.4),
						new Ponto(887.7, 486.3), new Ponto(902.5, 486.3), new Ponto(907.6, 498.3),
						new Ponto(925.2, 498.3), new Ponto(935.6, 517.9), new Ponto(943.8, 517.9),
						new Ponto(954.3, 536.6), new Ponto(928.3, 536.6), new Ponto(920.3, 522.0),
						new Ponto(907.2, 522.0), new Ponto(904.7, 528.9), new Ponto(890.3, 528.9),
						new Ponto(885.3, 517.5), new Ponto(849.0, 517.5), new Ponto(835.2, 490.0),
						new Ponto(841.0, 480.7) },
				"oc_indonesia", new Ponto(885, 492), 3);

		Territorio Nova_Zelandia = new Territorio("Nova Zelândia",
				new Ponto[] { new Ponto(928.8, 601.5), new Ponto(936.9, 601.5), new Ponto(943.0, 613.7),
						new Ponto(939.8, 616.5), new Ponto(944.2, 616.5), new Ponto(950.3, 628.6),
						new Ponto(931.9, 661.5), new Ponto(926.6, 661.5), new Ponto(917.6, 678.5),
						new Ponto(900.1, 678.5), new Ponto(917.6, 644.5), new Ponto(922.9, 644.5),
						new Ponto(932.3, 627.8), new Ponto(928.0, 619.1), new Ponto(934.1, 610.0) },
				"oc_novazelandia", new Ponto(929, 616), 2);

		Territorio Perth = new Territorio("Perth",
				new Ponto[] { new Ponto(856.7, 535.5), new Ponto(839.2, 535.5), new Ponto(822.3, 565.8),
						new Ponto(799.1, 565.8), new Ponto(789.6, 584.3), new Ponto(781.3, 584.3),
						new Ponto(775.3, 595.7), new Ponto(775.3, 598.3), new Ponto(782.3, 608.6),
						new Ponto(782.3, 610.7), new Ponto(770.9, 630.1), new Ponto(763.5, 630.1),
						new Ponto(756.7, 639.9), new Ponto(766.4, 655.8), new Ponto(780.3, 655.8),
						new Ponto(787.3, 641.9), new Ponto(816.7, 641.9), new Ponto(861.6, 562.1),
						new Ponto(855.6, 550.2), new Ponto(862.4, 542.3) },
				"oc_perth", new Ponto(804, 577), 1);

		// Carregando as fronteiras
		Alasca.setFronteira(Siberia);
		Alasca.setFronteira(Calgary);
		Alasca.setFronteira(Vancouver);
		Calgary.setFronteira(Groelandia);
		Calgary.setFronteira(Vancouver);
		Vancouver.setFronteira(Quebec);
		Vancouver.setFronteira(California);
		Vancouver.setFronteira(Texas);
		Quebec.setFronteira(Texas);
		Quebec.setFronteira(Nova_York);
		California.setFronteira(Texas);
		California.setFronteira(Mexico);
		Texas.setFronteira(Nova_York);
		Texas.setFronteira(Mexico);
		Mexico.setFronteira(Venezuela);
		Venezuela.setFronteira(Peru);
		Venezuela.setFronteira(Brasil);
		Peru.setFronteira(Brasil);
		Peru.setFronteira(Argentina);
		Brasil.setFronteira(Argentina);
		Brasil.setFronteira(Nigeria);
		Nigeria.setFronteira(Argelia);
		Nigeria.setFronteira(Egito);
		Nigeria.setFronteira(Somalia);
		Nigeria.setFronteira(Angola);
		Argelia.setFronteira(Egito);
		Egito.setFronteira(Somalia);
		Somalia.setFronteira(Angola);
		Somalia.setFronteira(Africa_do_Sul);
		Angola.setFronteira(Africa_do_Sul);
		Espanha.setFronteira(Franca);
		Franca.setFronteira(Italia);
		Italia.setFronteira(Polonia);
		Italia.setFronteira(Romenia);
		Polonia.setFronteira(Romenia);
		Polonia.setFronteira(Ucrania);
		Romenia.setFronteira(Ucrania);
		Suecia.setFronteira(Estonia);
		Suecia.setFronteira(Letonia);
		Estonia.setFronteira(Letonia);
		Estonia.setFronteira(Russia);
		Russia.setFronteira(Letonia);
		Russia.setFronteira(Siberia);
		Russia.setFronteira(Cazaquistao);
		Siberia.setFronteira(Cazaquistao);
		Letonia.setFronteira(Cazaquistao);
		Letonia.setFronteira(Turquia);
		Letonia.setFronteira(Polonia);
		Letonia.setFronteira(Ucrania);
		Turquia.setFronteira(Cazaquistao);
		Turquia.setFronteira(Ucrania);
		Turquia.setFronteira(Siria);
		Turquia.setFronteira(Paquistao);
		Turquia.setFronteira(China);
		Cazaquistao.setFronteira(China);
		Cazaquistao.setFronteira(Mongolia);
		China.setFronteira(Mongolia);
		China.setFronteira(Coreia_do_Norte);
		China.setFronteira(Coreia_do_Sul);
		China.setFronteira(Paquistao);
		China.setFronteira(India);
		Coreia_do_Norte.setFronteira(Coreia_do_Sul);
		Coreia_do_Sul.setFronteira(India);
		Coreia_do_Sul.setFronteira(Bangladesh);
		Coreia_do_Sul.setFronteira(Tailandia);
		India.setFronteira(Bangladesh);
		India.setFronteira(Paquistao);
		Bangladesh.setFronteira(India);
		Bangladesh.setFronteira(Tailandia);
		Siria.setFronteira(Paquistao);
		Siria.setFronteira(Jordania);
		Siria.setFronteira(Iraque);
		Siria.setFronteira(Ira);
		Jordania.setFronteira(Arabia_Saudita);
		Jordania.setFronteira(Iraque);
		Arabia_Saudita.setFronteira(Jordania);
		Arabia_Saudita.setFronteira(Iraque);
		Iraque.setFronteira(Ira);
		Ira.setFronteira(Paquistao);
		Australia.setFronteira(Perth);
		Australia.setFronteira(Nova_Zelandia);
		Groelandia.setFronteira(Reino_Unido);
		Quebec.setFronteira(Groelandia);
		Reino_Unido.setFronteira(Franca);
		Franca.setFronteira(Suecia);
		Suecia.setFronteira(Italia);
		Italia.setFronteira(Argelia);
		Espanha.setFronteira(Argelia);
		Romenia.setFronteira(Egito);
		Jordania.setFronteira(Egito);
		Arabia_Saudita.setFronteira(Somalia);
		Cazaquistao.setFronteira(Japao);
		Mongolia.setFronteira(Japao);
		Coreia_do_Norte.setFronteira(Japao);
		India.setFronteira(Indonesia);
		Bangladesh.setFronteira(Indonesia);
		Indonesia.setFronteira(Australia);
		Indonesia.setFronteira(Nova_Zelandia);
		Australia.setFronteira(Nova_Zelandia);

		// Adiciona os territorios aos continentes
		for (Continente c : lstContinentes) {
			if (c.getNome() == "América do norte") {
				c.getLstTerritorios().add(Alasca);
				c.getLstTerritorios().add(Calgary);
				c.getLstTerritorios().add(Groelandia);
				c.getLstTerritorios().add(Vancouver);
				c.getLstTerritorios().add(Quebec);
				c.getLstTerritorios().add(California);
				c.getLstTerritorios().add(Texas);
				c.getLstTerritorios().add(Nova_York);
				c.getLstTerritorios().add(Mexico);
			} else if (c.getNome() == "América do sul") {
				c.getLstTerritorios().add(Venezuela);
				c.getLstTerritorios().add(Peru);
				c.getLstTerritorios().add(Brasil);
				c.getLstTerritorios().add(Argentina);
			} else if (c.getNome() == "África") {
				c.getLstTerritorios().add(Africa_do_Sul);
				c.getLstTerritorios().add(Somalia);
				c.getLstTerritorios().add(Angola);
				c.getLstTerritorios().add(Egito);
				c.getLstTerritorios().add(Nigeria);
				c.getLstTerritorios().add(Argelia);
			} else if (c.getNome() == "Ásia") {
				c.getLstTerritorios().add(Tailandia);
				c.getLstTerritorios().add(Bangladesh);
				c.getLstTerritorios().add(India);
				c.getLstTerritorios().add(Coreia_do_Norte);
				c.getLstTerritorios().add(Coreia_do_Sul);
				c.getLstTerritorios().add(Paquistao);
				c.getLstTerritorios().add(Ira);
				c.getLstTerritorios().add(Iraque);
				c.getLstTerritorios().add(Arabia_Saudita);
				c.getLstTerritorios().add(Jordania);
				c.getLstTerritorios().add(Siria);
				c.getLstTerritorios().add(China);
				c.getLstTerritorios().add(Mongolia);
				c.getLstTerritorios().add(Turquia);
				c.getLstTerritorios().add(Cazaquistao);
				c.getLstTerritorios().add(Letonia);
				c.getLstTerritorios().add(Estonia);
				c.getLstTerritorios().add(Russia);
				c.getLstTerritorios().add(Siberia);
				c.getLstTerritorios().add(Japao);
			} else if (c.getNome() == "Europa") {
				c.getLstTerritorios().add(Reino_Unido);
				c.getLstTerritorios().add(Franca);
				c.getLstTerritorios().add(Espanha);
				c.getLstTerritorios().add(Suecia);
				c.getLstTerritorios().add(Italia);
				c.getLstTerritorios().add(Romenia);
				c.getLstTerritorios().add(Ucrania);
				c.getLstTerritorios().add(Polonia);
			} else if (c.getNome() == "Oceania") {
				c.getLstTerritorios().add(Australia);
				c.getLstTerritorios().add(Perth);
				c.getLstTerritorios().add(Indonesia);
				c.getLstTerritorios().add(Nova_Zelandia);
			}
		}
	}

	private Jogada descobreJogadas() {
		Jogada jogada = null;
		for (Jogada j : lstJogadas) {
			if (j.isAtivo()) {
				jogada = j;
			}
		}
		return jogada;
	}
	
	//funcao importante
	private void calculaSoldados() {
		int i = 0;
		model.Exercito exAtivo = null;
		
		//e is the current player
		for (model.Exercito e : controller.getLstJogadores()) {
			if (e.isAtivo()) {
				exAtivo = e;
			}
		}
			
		// Calcula quanto territorios o exercito tem
		for (Continente c : getLstContinentes()) {
			for (Territorio t : c.getLstTerritorios()) {
				/*
				if (t.getLstSoldados().get(0).getExercito() == exAtivo) {
					i++;
				}
				*/
			}
		}

		// Divide o número de continentes por 2
		i = i / 2;

		// Menor número de exércitos é 3
		if (i < 3) {
			i = 3;
		}

		// Insere os soldados na reserva
		for (; i > 0; i--) {
			exAtivo.addSoldado(new Soldado(exAtivo));
		}

		// Para cada continente
		for (Continente c : getLstContinentes()) {
			// Se o jogador da vez tem todos os territorios daquele continente
			if (calculaBonusContinente(c)) {
				System.out.println("Bonus continente " + c.getNome() + " " + c.getBonus());
				for (int j = c.getBonus(); j > 0; j--) {
					exAtivo.addSoldado(new Soldado(exAtivo, c));
				}

			}
		}

	}

	private boolean calculaBonusContinente(Continente c) {
		for (Territorio t : c.getLstTerritorios()) {
			if (t.getLstSoldados().get(0).getExercito() != jogadorDaVez) {
				return false;
			}
		}
		return true;
	}

	private void proxJogador() {
		if(itJogador == null){
			 itJogador = getLstJogadores().iterator();
		}
		
		// Desmarca quem está ativo na lista de jogadores
		for (model.Exercito e : controller.getLstJogadores()) {
			if (e.isAtivo()) {
				e.setAtivo();
			}
		}

		// Move o iterator para o próximo jogador da lista de jogadores
		Exercito jogadorDaVez = null;

		if (!itJogador.hasNext()) {
			itJogador = controller.getLstJogadores().iterator();
		}

		jogadorDaVez = itJogador.next();

		// Marca como o jogador ativo
		jogadorDaVez.setAtivo();
		setJogadorDaVez();
		// setMensagem("Vez do Jogador " + jogadorDaVez.getNome());
		notificaMudancas();

	}

	private Exercito descobreExercito(String nome) {
		Exercito jogador = null;
		for (Exercito e : lstJogadores) {
			if (e.getNome() == nome) {
				jogador = e;
			}
		}
		return jogador;
	}

	public boolean possuiTerritorio(String nome) {

		return possuiTerritorio(descobreExercito(nome));

	}

	public boolean possuiTerritorio(Exercito e) {

		for (Continente c : lstContinentes) {
			for (Territorio t : c.getLstTerritorios()) {
				if(t.getLstSoldados().size() > 0){
					if (t.getLstSoldados().get(0).getExercito() == e) {
						return true;
					}
				}
			}
		}

		e.setJogadorFora(true);

		if (e.isJogadorFora()) {
			ArrayList<Carta> lstCartaAux = new ArrayList<>(e.getLstCartas());
			for (Carta c : e.getLstCartas()) {
				//moveEntreListas(lstCartaAux, jogadorDaVez.getLstCartas(), c);
			}
			e.getLstCartas().clear();
		}
		
		//notificaMudancas();

		return false;

	}

	/**
	 * 
	 */
	public void preparaTabuleiro() {
		
		
		if (lstJogadores.size() > 0) {
			myself = getMyself() != null ? getMyself() : getLstJogadores().get(0);
			System.out.println("Comparando myself " + myself);
			qtdTroca = 4;
			deck = Deck.getInstance();
			proxJogador();
			embaralhaLista(deck.getLstCartas());
			distribuiCartasInicio();
			distribuiObjetivos();
			distribuirExercitosInicio();
			proxJogada();
		}
	}

	private void distribuiObjetivos() {

		deckObjetivos = DeckObjetivos.getInstance();

		// Iterador da lsita de objetivos do deck de objetivos começando do
		// indice 0
		Iterator<Objetivo> itDeckObjetivo = deckObjetivos.getLstObjetivos().listIterator(0);

		// Para cada jogador
		for (Exercito e : lstJogadores) {

			// Pega o objetivo no iterador
			Objetivo o = itDeckObjetivo.next();

			// Se não é objetivo de destruir exercito
			if (o.getExercitoAlvo() == null) {

				e.setObjetivo(o);

			}

			// Se for objetivo de destruir exército
			else if (getLstJogadores().contains(o.getExercitoAlvo())) {

				// Se o exército alvo é o exercito da vez da distribuição dos
				// objetivos
				if (o.getExercitoAlvo() == e) {

					// Seta o objetivo de 24 territórios
					e.setObjetivo(new Objetivo_1());
				}

				// Se o exército não é o exercito alvo,
				else {
					// Seta o objetivo
					e.setObjetivo(o);
				}
			}
		}

	}
	
	//importante
	//o deck vai ser embaralhado só uma vez, no ínicio para evitar ter que passar múltiplas strings
	private void distribuiCartasInicio() {

		deck.embaralhaDeck();
		deck.embaralhaDeck();

		for (Carta c : getDeck().getLstCartas()) {

			if (!c.isCoringa()) {
				if (!itJogador.hasNext()) {
					itJogador = getLstJogadores().iterator();
				}
				itJogador.next().addCarta(c);
			}
		}
		itJogador = getLstJogadores().iterator();
	}

	private void distribuirExercitosInicio() {
		for (model.Exercito e : getLstJogadores()) {
			for (Carta c : e.getLstCartas()) {
				c.getTerritorio().addSoldado(new Soldado(e));
			}
		}
		// Limpa a lista de cartas depois da distribuição
		for (Exercito j : getLstJogadores()) {
			j.getLstCartas().clear();
		}
	}

	public static void embaralhaLista(List<?> lst) {
		long seed = 1L;
		Collections.shuffle(lst, new Random(seed));
	}

	private Territorio descobreTerritorioClicado(int x, int y) {

		Territorio territorio = null;

		for (Continente c : getLstContinentes()) {
			for (Territorio t : c.getLstTerritorios()) {
				if (t.getShape().contains(x, y)) {
					territorio = t;
				}
			}
		}

		return territorio;
	}

	private <T> void moveEntreListas(Collection<T> lstOrigem, Collection<T> lstDestino, T objeto) {
		lstOrigem.remove(objeto);
		lstDestino.add(objeto);
	}

	private void proxJogada() {
		//TO BE IMPLEMENTED
		//Mandar servidor enviar proxJogada nos outros clientes
		
		notificaMudancas();
		for (Jogada j : controller.getLstJogadas()) {
			if (j.isAtivo()) {
				j.setAtivo();
			}
		}

		if (!itJogada.hasNext()) {
			itJogada = controller.getLstJogadas().iterator();
			if (conquistouTerritorio) {
				moveEntreListas(deck.getLstCartas(), jogadorDaVez.getLstCartas(), deck.getLstCartas().get(0));
				setMensagem(jogadorDaVez.getLstCartas().get(jogadorDaVez.getLstCartas().size() - 1).getImagem());

			}
			conquistouTerritorio = false;
			proxJogador();

			while (possuiTerritorio(jogadorDaVez.getNome()) == false) {
				proxJogador();
			}

			calculaSoldados();
		}

		itJogada.next().setAtivo();
		zeraSoldadosImigrantes();
		String mensagem = "Vez do Jogador " + jogadorDaVez.getNome() + " " + descobreJogadas().getNome();
		if (descobreJogadas().getNome() == "Remanejar") {
			mensagem += " (botão esquerdo seleciona origem, botão direito move soldados)";
		}
		setMensagem(mensagem);

	}

	private void setJogadorDaVez() {
		for (Exercito e : lstJogadores) {
			if (e.isAtivo()) {
				jogadorDaVez = e;
			}
		}
	}

	public ArrayList<Continente> getLstContinentes() {
		return lstContinentes;
	}

	public int getNumeroDado(char tipo, int ordem) {
		if (tipo == 'a') {
			return lstDadosAtaque.get(ordem).getNumero();
		} else {
			return lstDadosDefesa.get(ordem).getNumero();
		}
	}

	public Exercito getJogadorDaVez() {
		return jogadorDaVez;
	}

	public Territorio getTerritorioOrigem() {
		if(territorioOrigem != null){
			//System.out.println("Territorio origem" + territorioOrigem.getNome());
		}
		return territorioOrigem;
	}

	public Territorio getTerritorioDestino() {
		if(territorioDestino != null){
			//System.out.println("Territorio destino" + territorioDestino.getNome());
		}
		return territorioDestino;
	}

	public static ControllerTabuleiro getInstance() {
		if (lstJogadores.size() > 0) {
			if (controller == null) {
				if(isMaster() && seedSeted == false){
					seed = System.nanoTime();
					SerializeData.getInstance().sendData("Seed", Long.toString(seed));
					seedSeted = true;
				}
				controller = new ControllerTabuleiro();
			}
		}
		return controller;
	}

	public List<Jogada> getLstJogadas() {
		return lstJogadas;
	}

	public static List<model.Exercito> getLstJogadores() {
		return lstJogadores;
	}

	public Object[] getCartaJogador(int i) {

		Object retorno[];
		retorno = new Object[3];

		if (jogadorDaVez.getLstCartas().size() > 0) {
			if (i < jogadorDaVez.getLstCartas().size()) {
				retorno[0] = jogadorDaVez.getLstCartas().get(i).getImagem();
				retorno[1] = jogadorDaVez.getLstCartas().get(i).getTerritorio() != null
						? jogadorDaVez.getLstCartas().get(i).getTerritorio().getNome() : null;
				retorno[2] = jogadorDaVez.getLstCartas().get(i).getSimbolo();

				return retorno;
			}
		}
		return null;
	}

	private Carta descobreCarta(String nomeTerritorio) {
		for (Carta c : jogadorDaVez.getLstCartas()) {
			if (c.getTerritorio() != null) {
				if (c.getTerritorio().getNome() == nomeTerritorio) {
					return c;
				}
			} else {
				if (c.isCoringa()) {
					return c;
				}
			}
		}
		return null;
	}

	public void devolveCartaAoDeck(Carta c) {
		moveEntreListas(jogadorDaVez.getLstCartas(), deck.getLstCartas(), c);
	}

	public void executaTroca(String[] TerritorioCartas) {

		if (vencedor == null) {

			// Adiciona os soldados referente a troca atual
			for (int i = qtdTroca; i > 0; i--) {
				jogadorDaVez.getLstSoldados().add(new Soldado(jogadorDaVez));
			}
			String mensagem = "Você ganhou " + qtdTroca + " soldados pela troca";
			// Incrementa a quantidade de exercitos para a proxima troca
			somaTroca();

			// Adicionando os soldados nos territorios caso alguma carta da
			// troca seja de territorio dele
			for (int i = 0; i < TerritorioCartas.length; i++) {
				Carta carta = descobreCarta(TerritorioCartas[i]);
				if (carta.getTerritorio() != null
						&& carta.getTerritorio().getLstSoldados().get(0).getExercito() == jogadorDaVez) {
					carta.getTerritorio().addSoldado(new Soldado(jogadorDaVez));
					mensagem += " + 1 (" + carta.getTerritorio().getNome() + ")";
				}
				devolveCartaAoDeck(carta);
			}

			setMensagem(mensagem);
		}

	}

	private void somaTroca() {

		if (qtdTroca < 20) {
			qtdTroca += 2;
		} else {
			qtdTroca += 5;
		}

	}

	public Deck getDeck() {
		return deck;
	}

	public Jogada getJogadaAtual() {
		Jogada jogada = null;
		for (Jogada j : getLstJogadas()) {
			if (j.isAtivo()) {
				jogada = j;
			}
		}
		return jogada;
	}

	public void setTerritorioOrigem(Territorio territorioOrigem) {
		this.territorioOrigem = territorioOrigem;
		this.territorioDestino = null;
		String string1 = null;
		if (descobreJogadas().getNome() == "Atacar") {
			string1 = "Origem: ";
		} else if (descobreJogadas().getNome() == "Remanejar") {
			string1 = "Remanejando soldados a partir de: ";
		}
		if (getTerritorioOrigem() != null) {
			setMensagem(string1 + getTerritorioOrigem().getNome());
		}
		notificaMudancas();
	}

	public void setTerritorioDestino(Territorio territorioDestino) {
		this.territorioDestino = territorioDestino;
		if (getTerritorioDestino() != null) {
			setMensagem(
					"Origem: " + getTerritorioOrigem().getNome() + " | Destino: " + getTerritorioDestino().getNome());
		}
		notificaMudancas();
	}
	
	public static void setJogador(String s, Object cor) {
		lstJogadores.add(new Exercito(s, cor));
	}

	private void ordenaLstDados() {
		Collections.sort(lstDadosAtaque);
		Collections.sort(lstDadosDefesa);

		notificaMudancas();
	}

	public void JogaDados(char tipo, int numDados) {

		if (descobreJogadas().getNome() == "Atacar") {

			if (getTerritorioOrigem() != null && getTerritorioDestino() != null) {

				ArrayList<Dado> lstDados = null;

				// Define se vai ser sorteado na lista de ataque ou lista de
				// defesa.
				if (tipo == 'a') {
					lstDados = lstDadosAtaque;
				} else {
					lstDados = lstDadosDefesa;
				}

				// Marca todos os dados como falso
				for (Dado d : lstDados) {
					d.setNumero(0);
				}
				
				// Joga os dados
				for (int i = 0; i < numDados; i++) {
					lstDados.get(i).jogaDado();
				}

				ordenaLstDados();

			} else {
				// setMensagem("Selecione um territorio de Ataque e um de
				// Defesa");
			}

		} else {
			setMensagem("Não é jogada de ataque");
			notificaMudancas();
		}
		
		notificaMudancas();
		
	}

	public void btnProxJogada_click() {
		if (vencedor == null) {
			setTerritorioDestino(null);
			setTerritorioOrigem(null);
			proxJogada();
		}
	}

	public void notificaMudancas() {
		// Se o objetivo do jogador da vez dor diferente de nulo e o jogo ainda não possuir vencedor
		if (jogadorDaVez.getObjetivo() != null && vencedor == null) {
			// Se o check do objetivo do jogador for igual a true
			if (jogadorDaVez.getObjetivo().getExercitoAlvo() == null && jogadorDaVez.getObjetivo().Check(lstContinentes, jogadorDaVez)) {
				setVencedor();
				telaVencedor();
			} else if(jogadorDaVez.getObjetivo().getExercitoAlvo() != null && jogadorDaVez.getObjetivo().Check(lstContinentes, jogadorDaVez.getObjetivo().getExercitoAlvo())) {
				setVencedor();
				telaVencedor();
			}
		}

		setChanged();
		notifyObservers();
	}

	private void setVencedor() {
		this.vencedor = jogadorDaVez;
	}

	public void btnJogarDados_click() {
		
		Territorio territorioTemp = null;

		if (vencedor == null) {

			if (getJogadaAtual().getNome() == "Atacar" && territorioOrigem.getLstSoldados().size() > 1
					&& getTerritorioOrigem() != null && getTerritorioDestino() != null) {
				int qtdDadosAtaque = getTerritorioOrigem().getLstSoldados().size() > 3 ? 3
						: getTerritorioOrigem().getLstSoldados().size() - 1;
				int qtdDadosDefesa = getTerritorioDestino().getLstSoldados().size() > 3 ? 3
						: getTerritorioDestino().getLstSoldados().size();

				controller.JogaDados('a', qtdDadosAtaque);
				controller.JogaDados('d', qtdDadosDefesa);
				
				SerializeData.getInstance().sendData("Dados", qtdDadosAtaque, qtdDadosDefesa, lstDadosAtaque, lstDadosDefesa);
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				territorioTemp = getTerritorioDestino();
				for (Dado da : lstDadosAtaque) {
					if (da.getNumero() != 0) {
						Dado df = lstDadosDefesa.get(lstDadosAtaque.indexOf(da));
						if (df.getNumero() != 0) {
							int comparaResultado = df.compareTo(da);
							if (comparaResultado <= 0) {
								territorioOrigem.getLstSoldados().remove(0);
								// console.addLog("Retira 1 ataque");
								SerializeData.getInstance().sendData("Atacar", "Derrota", getTerritorioOrigem().getNome(), getTerritorioOrigem().getLstSoldados().get(0).getExercito().getNome(), Integer.toString(getTerritorioOrigem().getLstSoldados().size()), territorioTemp.getNome(), territorioTemp.getLstSoldados().get(0).getExercito().getNome(), Integer.toString(territorioTemp.getLstSoldados().size()), Integer.toString(qtdDadosAtaque));
							}
							if (comparaResultado > 0) {
									Vitoria(territorioOrigem, territorioDestino, qtdDadosAtaque);
									SerializeData.getInstance().sendData("Atacar", "Vitoria", getTerritorioOrigem().getNome(), getTerritorioOrigem().getLstSoldados().get(0).getExercito().getNome(), Integer.toString(getTerritorioOrigem().getLstSoldados().size()), territorioTemp.getNome(), territorioTemp.getLstSoldados().get(0).getExercito().getNome(), Integer.toString(territorioTemp.getLstSoldados().size()), Integer.toString(qtdDadosAtaque));
									//conquistou territorio
								
							}
							//TO BE IMPLEMENTED (OVERLOAD)
							//v1 = Fase do turno, v2 = nome do territorioOrigem, v3 = Cor do exercito, v4 = size dos soldados, v5, v6 e v7 eh o mesmo so que para o destino
							//SerializeData.getInstance().sendData("Atacar", getTerritorioOrigem().getNome(), getTerritorioOrigem().getLstSoldados().get(0).getExercito().getNome(), Integer.toString(getTerritorioOrigem().getLstSoldados().size()), getTerritorioDestino().getNome(), getTerritorioDestino().getLstSoldados().get(0).getExercito().getNome(), Integer.toString(getTerritorioOrigem().getLstSoldados().size()));
														
					}
				}
			}
		}
	}
}

	public void telaVencedor() {
		setMensagem("Exercito " + vencedor.getNome() + " ganhou o jogo! Objetivo: "
				+ jogadorDaVez.getObjetivo().getDescricao());
	}

	public Exercito getVencedor() {
		return this.vencedor;
	}

	private boolean moveSoldadoContinente(Continente c, Territorio t) {
		ListIterator<Soldado> itSoldado = jogadorDaVez.getLstSoldados().listIterator(0);

		while (itSoldado.hasNext()) {
			Soldado s = itSoldado.next();
			if (s.getContinente() == c) {
				moveEntreListas(jogadorDaVez.getLstSoldados(), t.getLstSoldados(), s);
				setMensagem("Soldado bunus do continente " + s.getContinente().getNome() + " alocado no territorio "
						+ t.getNome());
				return true;
			}
		}
		return false;
	}

	public boolean moveSoldadoAvulso(Territorio t) {
		ListIterator<Soldado> itSoldado = jogadorDaVez.getLstSoldados().listIterator(0);

		while (itSoldado.hasNext()) {
			Soldado s = itSoldado.next();
			if (s.getContinente() == null) {
				moveEntreListas(jogadorDaVez.getLstSoldados(), t.getLstSoldados(), s);
				setMensagem("Soldado avulso alocado no territorio " + t.getNome());
				return true;
			}
		}
		return false;
	}

	private void zeraSoldadosImigrantes() {

		for (Continente c : lstContinentes) {
			for (Territorio t : c.getLstTerritorios()) {
				for (Soldado s : t.getLstSoldados()) {
					if (s.isImigrante()) {
						s.setImigrante();
					}
				}
			}
		}
	}

	public void pnlMapa_click(int x, int y, int botaoMouse) {
		if(getJogadorDaVez() == myself){
			if (vencedor == null) {
	
				Territorio t = descobreTerritorioClicado(x, y);
				Continente c = descobreContinenteClicado(x, y);
	
				if (t != null) {
					Exercito e = t.getLstSoldados().get(0).getExercito();
					// Jogada de distribuição
					if (descobreJogadas().getNome() == "Distribuir" && jogadorDaVez.getLstCartas().size() < 5) {
	
						if (e == jogadorDaVez) { // Se o territorio clicado for do
													// jogador da vez
	
							if (moveSoldadoContinente(c, t)) {
	
							} else if (moveSoldadoAvulso(t)) {
	
							} else if (jogadorDaVez.getLstSoldados().size() > 0) {
								setMensagem(
										"Soldados existentes na reserva são soldados de bônus por conquista de continente.");
							} else {
								setMensagem("Não há mais soldados na reserva");
							}
							
							//TO BE IMPLEMENTED (OVERLOAD)
							SerializeData.getInstance().sendData("Distribuir", t.getNome());
	
						}
						notificaMudancas();
						
					}
	
					// Jogada de Ataque
					if (descobreJogadas().getNome() == "Atacar") {
						if (e == jogadorDaVez) { // Se o territorio for do jogador da vez
							if (t.getLstSoldados().size() > 1) {
								setTerritorioOrigem(t); // Seta o territorio clicado como territorio de origem
								notificaMudancas();
							}
						} else {
							// Se houver territorio origem e o territorio clicado está na lista de territorio
							// de origem
							if (getTerritorioOrigem() != null && getTerritorioOrigem().getLstFronteiras().contains(t)) { 
								setTerritorioDestino(t);
							}
						}
					}
	
					// Jogada de remanejamento
					if (descobreJogadas().getNome() == "Remanejar") {
	
						if (botaoMouse == MouseEvent.BUTTON1) {
	
							if (e == jogadorDaVez) {
								if (getTerritorioOrigem() != t) {
									setTerritorioOrigem(t);
								} else {
									setTerritorioOrigem(null);
								}
							}
	
						} else if (botaoMouse == MouseEvent.BUTTON3) {
							if (e == jogadorDaVez) {
								if (getTerritorioOrigem() != null && getTerritorioOrigem().getLstSoldados().size() > 1
										&& getTerritorioOrigem().getLstFronteiras().contains(t)) {
									Soldado soldado = getTerritorioOrigem().getLstSoldados().get(0);
									if (!soldado.isImigrante()) {
										moveEntreListas(getTerritorioOrigem().getLstSoldados(), t.getLstSoldados(),
												soldado);
										soldado.setImigrante();
									} else {
										setMensagem(
												"Os soldados restantes são imigrantes e so poderão ser movidos na próxima rodada.");
									}
	
								} else {
									if (getTerritorioOrigem().getLstFronteiras().contains(t)) {
										setMensagem("Você não pode deixar seu território vazio");
									} else {
										setMensagem("O territorio " + getTerritorioOrigem().getNome()
												+ " não faz fronteira com o territororio " + t.getNome());
									}
								}
							} else {
								setMensagem("O territorio " + t.getNome() + "  não pertence ao exercito "
										+ jogadorDaVez.getNome());
							}
						}
	
						notificaMudancas();
						//TO BE IMPLEMENTED (OVERLOAD)
						//SerializeData.getInstance().sendData("Atacar", getTerritorioOrigem().getNome(), getTerritorioOrigem().getLstSoldados().get(0).getExercito().getNome(), Integer.toString(getTerritorioOrigem().getLstSoldados().size()), territorioTemp.getNome(), territorioTemp.getLstSoldados().get(0).getExercito().getNome(), Integer.toString(territorioTemp.getLstSoldados().size()));
						SerializeData.getInstance().sendData("Remanejar", getTerritorioOrigem().getNome(), getTerritorioOrigem().getLstSoldados().get(0).getExercito().getNome(), Integer.toString(getTerritorioOrigem().getLstSoldados().size()), t.getNome(), t.getLstSoldados().get(0).getExercito().getNome(), Integer.toString(t.getLstSoldados().size()));
					}
	
				}
			}
		}
	}

	private Continente descobreContinenteClicado(int x, int y) {
		for (Continente c : getLstContinentes()) {
			for (Territorio t : c.getLstTerritorios()) {
				if (t.getShape().contains(x, y)) {
					return c;
				}
			}
		}
		return null;
	}

	public int getQtdSoldadosReserva(String nome) {
		return descobreExercito(nome).getLstSoldados().size();
	}

	public boolean isJogadorAtivo(String nome) {
		return descobreExercito(nome).isAtivo();
	}

	public boolean validaTroca(HashSet<String> lstTroca) {
		return false;
	}

	public static boolean isMaster() {
		return master;
	}

	public static void setMaster() {
		System.out.println("Master seted to true");
		master = true;
	}



	public view.Exercito getClientPlayer() {
		return clientPlayer;
	}
	

	public static void setClientPlayer(view.Exercito e) {
		clientPlayer = e;
	}



	public Exercito getMyself() {
		for(Exercito ex : getLstJogadores()){
			System.out.println("Comparing " + ex.getNome());
			if(ex.getNome() == viewMyself.getNome())
				return ex;
		}
		return null;
	}
	
	public void Vitoria(Territorio territorioOrigem, Territorio territorioDestino, int qtdDadosAtaque){
		territorioDestino.getLstSoldados().remove(0);
		if (territorioDestino.getLstSoldados().size() <= 0) {
			for (int i = 0; i < qtdDadosAtaque; i++) {
				moveEntreListas(territorioOrigem.getLstSoldados(),
						territorioDestino.getLstSoldados(),
						territorioOrigem.getLstSoldados().get(0));
			}
			conquistouTerritorio = true;
			
			if(jogadorDaVez != null && getTerritorioDestino() != null)																															
				setMensagem("Exercito " + jogadorDaVez.getNome() + " invadiu o(a) "
					+ getTerritorioDestino().getNome());
			setTerritorioDestino(null);
			//SerializeData.getInstance().sendData("Atacar", "Vitoria", getTerritorioOrigem().getNome(), getTerritorioOrigem().getLstSoldados().get(0).getExercito().getNome(), Integer.toString(getTerritorioOrigem().getLstSoldados().size()), territorioTemp.getNome(), territorioTemp.getLstSoldados().get(0).getExercito().getNome(), Integer.toString(territorioTemp.getLstSoldados().size()));
			//conquistou territorio
		}
	}



	public static void setMyself(view.Exercito myself) {
		viewMyself = myself;
	}
	
	public static long getSeed(){
		return seed;
	}
	
	public static void setSeed(long seedMaster){
		seed = seedMaster;
	}

}