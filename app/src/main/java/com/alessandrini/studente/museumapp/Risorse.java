            package com.alessandrini.studente.museumapp;

import android.media.Image;

            public class Risorse {

    int numero_stanze = 14;

    int []  immagini = { // immagini delle sale
            R.drawable.sala1,
            R.drawable.sala2,
            R.drawable.sala3,
            R.drawable.sala4,
            R.drawable.sala5,
            R.drawable.sala6,
            R.drawable.sala7,
            R.drawable.sala8,
            R.drawable.sala9,
            R.drawable.sala10,
            R.drawable.sala11,
            R.drawable.sala12,
            R.drawable.sala13,
            R.drawable.sala14
    };

    int []  oggetti = { // immagini degli oggetti
            R.drawable.ogg1,
            R.drawable.ogg2,
            R.drawable.ogg3,
            R.drawable.ogg4,
            R.drawable.ogg5,
            R.drawable.ogg6,
            R.drawable.ogg7,
            R.drawable.ogg8,
            R.drawable.ogg9,//nice
            R.drawable.ogg10,
            R.drawable.ogg11,
            R.drawable.ogg12,
            R.drawable.ogg13,//nice
            R.drawable.ogg14
    };


    String linkSale [] = {
            "http://www.gentidabruzzo.com/?page_id=418",
            "http://www.gentidabruzzo.com/?page_id=420",
            "http://www.gentidabruzzo.com/?page_id=422",
            "http://www.gentidabruzzo.com/?page_id=424",
            "http://www.gentidabruzzo.com/?page_id=506",
            "http://www.gentidabruzzo.com/?page_id=509",
            "http://www.gentidabruzzo.com/?page_id=512",
            "http://www.gentidabruzzo.com/?page_id=514",
            "http://www.gentidabruzzo.com/?page_id=516",
            "http://www.gentidabruzzo.com/?page_id=521",
            "http://www.gentidabruzzo.com/?page_id=524",
            "http://www.gentidabruzzo.com/?page_id=526",
            "http://www.gentidabruzzo.com/?page_id=528",
            "http://www.gentidabruzzo.com/?page_id=1249"
    };


    String [] titoli = {
            "L'archeologia dalla Preistoria al Medioevo",
            "Sacralità delle Grotte e continuità dei Luoghi di Culto",
            "Continuità dei riti sacri e della cultura materiale",
            "Il pastore e il suo corredo",
            "La capanna in pietra a secco e la transumanza",
            "Gli stazzi e la produzione del formaggio",
            "Il grano: dal seme alla farina",
            "Dall'oliveto all'olio. I mezzi di trasporto e raccolta del fieno",
            "La vite e il vino. Le eccellenze del nostro territorio",
            "La casa: struttura, arredo e vita domestica",
            "Il lino e la lana: produzione, filatura e tessitura",
            "Vesti e ornamenti: dal quotidiano al cerimoniale",
            "La Maiolica",
            "Il carcere borbonico e la borghesia",
    };
    String [] descrizioniStanza = { // descrizioni
            "La prima sala è dedicata al Paleolitico. La presenza più antica dell’uomo in Abruzzo risale a circa 650.000 anni fa con l’Homo Erectus, i cui primi rudimentali manufatti sono visibili nella prima vetrina. I manufatti litici relativi al Paleolitico medio rivelano la grande padronanza della tecnica percussiva da parte dell’uomo di Neanderthal che vive sulle montagne abruzzesi a partire da 100.000 anni fa e testimoniano l’evoluzione di un uomo che ricerca negli oggetti di pietra anche una dimensione estetica, un uomo capace di attribuire valenze simboliche a forme geometriche e che riconosce nelle forze della natura entità superiori.",
            "Nel Neolitico, con l’avvento dell’agricoltura e degli insediamenti all’aperto in villaggi di capanne, le grotte non vengono più utilizzate come rifugi temporanei per i cacciatori nomadi, ma come luoghi di culto per la divinità, identificata nella Madre Terra, fonte di vita. Per offrirle sacrifici propiziatori per i raccolti, i primi agricoltori cercano un contatto più diretto con la terra proprio attraverso le grotte. In esse scavano buche in cui gettare offerte rituali o compongono circoli di pietra nei quali venivano posti oggetti vari: vasi, macine, pesi o ossa di volatile.",
            "La terza sala è incentrata sul tema della continuità. Esiste, infatti, una certa continuità nell’uso e nella fattura degli oggetti, nelle tecniche e nei decori, dalla preistoria fino all’affermarsi dell’industrializzazione, che negli anni Cinquanta del secolo scorso portò alla standardizzazione dei modelli produttivi. Nell’esposizione si parte dagli oggetti d’uso e si osservano, ad esempio, la ricottiera e gli attingitoi, il trapano a volano di derivazione preistorica, usato fino a qualche decennio fa per riparare piatti e vasi praticando fori sulla linea di rottura e legando i pezzi con il fil di ferro. Lo stesso utilizzo millenario si è avuto per gli strumenti della filatura come ad esempio i fusi e le lucerne ad olio.",
            "La quarta sala è dedicata all’arte e al corredo del pastore, dove vi possono infatti osservare gli abiti tradizionali dei pastori. Gli indumenti principali erano la giacca in pelle di pecora o di capra che serviva per proteggersi dal freddo e dai rovi, i gambali, gli scarponi chiodati e le calzature estive in cuoio, le cosiddette “chiochie”.",
            "Questa sala illustra le attività materiali relative alla cultura transumante. Nella prima parte è proposta la ricostruzione in scala del villaggio di capanne pastorali che si trova in località Colle Civita presso Roccamorice, in provincia di Pescara. Si tratta di costruzioni in pietra nuda, a secco, destinate ad un uso prevalentemente pastorale. La capanna di pietra a secco o “tholos” è presente in tutta l’Europa e nel Medio Oriente, ma è particolarmente diffusa nei paesi del bacino del Mediterraneo, come testimonierebbero le antiche tholoi micenee, utilizzate a scopo funerario, i nuraghi sardi, che hanno probabilmente una funzione militare, le “castelle” corsiche e i trulli di Alberobello in Puglia.",
            "Nella sala 6 si può ammirare una suggestiva ricostruzione, a grandezza naturale, di una capanna a tholos in pietra a secco. Osservandola si può notare la funzionalità della sua elementare statica che permette, in particolare, di risolvere agevolmente il problema della copertura: le pietre della cupola sono aggettanti verso l’interno così da assicurare un tetto senza l’uso di leganti né di materiali di legno o comunque diversi dalla pietra. L’arredo del ricovero temporaneo del pastore era ridotto all’essenziale, si possono osservare gli utensili principali. Parte di questa attrezzatura veniva riposta su un palo in legno infisso nel terreno, detto “arciclocco”, che si trova nella ricostruzione accanto alla capanna e sul quale si potevano appendere il paiolo, detto “callaro” e i cestini di giunco, le “friscelle\", che servivano per fare il formaggio.",
            "Nella sala 7, che è la prima di una serie dedicata all’agricoltura, è possibile osservare un plastico rappresentativo della vita contadina abruzzese. Base dell’alimentazione delle popolazioni mediterranee fin dal Neolitico, i cereali, ed in particolar modo il grano, costituiscono il fulcro del ciclo agricolo annuale. Nel plastico si possono osservare alcune fasi della produzione della farina, realizzata attraverso strumenti e metodi rimasti pressoché immutati dalla preistoria.",
            "La sala 8 é divisa tematicamente in due parti: da una parte si possono osservare i mezzi di trasporto utilizzati nel mondo agricolo, dall’altra gli utensili e gli strumenti indispensabili per la molitura, cioè la produzione dell’olio, e quelli per la raccolta del fieno. Nel vecchio mondo agricolo sono stati impiegati mezzi di trasporto di tre tipi a seconda delle distanze, del territorio e dei carichi da trasferire: il corpo umano, gli animali da carico, i veicoli a trazione animale.",
            "La sala 9 illustra gli utensili e i metodi di produzione del vino, attività antichissima che in Abruzzo sembra avere origine in epoca preromana. Fino alla prima metà del ‘900, la coltivazione della vite in Abruzzo aveva soprattutto finalità di produzione per il consumo domestico del vino. Nel XIX secolo veniva prodotta uva di esportazione solo in alcune località a particolare vocazione, come l’alta Valle del Pescara, dove si producevano anche vini speciali: liquorosi o moscati. Solo negli anni Settanta inizierà una vera produzione enologica moderna che porterà alla produzione di vini di qualità tra i quali, oggi, i quattro DOC: il Montepulciano d’Abruzzo, il Cerasuolo, il Bianco Trebbiano e il Controguerra.",
            "La casa era il centro materiale e simbolico di tutta la vita rurale tradizionale, perché vi si celebravano tutti gli eventi e i momenti importanti del ciclo della vita e di quello dell’anno. Alla diversità di condizioni ambientali e socio-economiche che intercorre tra le zone montane e quelle collinari e marittime, si accompagna la diversità nella distribuzione sul territorio degli insediamenti abitativi e dei materiali edilizi.",
            "La sala 11 mostra la lavorazione delle fibre tessili, che è stata un’attività essenzialmente femminile e domestica, destinata in prevalenza all’autoconsumo, anche se, per la lana, si sono avuti nel passato importanti centri di manifattura artigianale, con prodotti destinati al commercio. Nella sala sono presenti diversi macchinari destinati alla lavorazione artigianale delle fibre tessili, oltre a diversi manufatti artigianali.",
            "Nella società tradizionale, il matrimonio, dopo la nascita, rappresentava l’evento più importante nel ciclo della vita di una persona: formare una famiglia era fondamentale nella trasmissione di valori culturali. Fin dall’infanzia regole morali e comportamentali esaltavano l’integrità e “l’onore” della futura sposa e della famiglia di provenienza; le donne si abituavano, quindi, a sviluppare atteggiamenti cosiddetti femminili, avvalendosi anche di consuetudini finalizzate a propiziare fecondità e abbondanza. Nella sala, accanto all’abito tradizionale, si può osservare un esempio di carta dotale che conteneva l’elenco dettagliato degli oggetti che la sposa portava in dote. In quest’occasione la neofidanzata riceveva il primo dono prezioso costituito, generalmente, da un paio di orecchini o da un ciondolo. L’anello, invece, le spettava il giorno del fidanzamento ufficiale, alla “promessa”, e l’evento era festeggiato con un ricevimento di parenti e amici.",
            "Nella sala 13 del Museo sono esposte testimonianze delle più antiche maioliche abruzzesi. La maiolica è una raffinata tipologia di ceramica, di derivazione islamica, con la caratteristica presenza di uno smalto. In Europa è stata prodotta solo in poche nazioni tra cui la Spagna e l’Italia che hanno avuto la più lunga tradizione grazie ai loro rapporti più stretti con la cultura islamica. In Abruzzo comincia ad essere prodotta nel XIII secolo, contemporaneamente alle prime attestazioni italiane. Occupa un’intera sala del Museo per il ruolo avuto in questa regione che è stata tra le più importanti aree di produzione della maiolica dell’Occidente. Vi si realizzavano le più diverse categorie di oggetti, dai contenitori più pregiati appartenenti ai servizi da tavola e da farmacia, fino ai vasi da fiori e ai bacili da barba; anche giochi per bambini, oggetti destinati al culto e molti utensili, dagli schiacciapatate ai portacandele, spesso erano in maiolica. Questa era impiegata anche nell’edilizia: proprio in Abruzzo si hanno le uniche testimonianze italiane di antichi soffitti e di facciate esterne di chiese, in maiolica. Nella sala, in alto, è riprodotto il soffitto seicentesco della chiesa di San Donato, a Castelli. Ma venivano realizzati anche mattoni da pavimento e rivestimenti per cucine.",
            "Nei locali dell’antica caserma di cavalleria della fortezza di Pescara, trasformati nell’Ottocento in Bagno Penale dai Borboni, furono imprigionati dal 1850 al 1860 molti dei maggiori protagonisti abruzzesi del Risorgimento, il grande movimento di pensiero e di azione che condusse alla nascita dell’Italia Unita.  Questi giovani rivoluzionari appartenevano a quella intellettualità romantica che si batteva per la libertà e l’unità d’Italia, esprimevano la lotta ceti borghesi urbani per il riconoscimento del loro ruolo sociale e per gli ideali di libertà e democrazia, in sostituzione del potere aristocratico feudale. In questo carcere, venivano incatenati in coppia e messi insieme, per aggravio di pena, ai detenuti per reati comuni."
    };

    String [] titoliOggetti = { //titoli ObjectActivity da cambiare
            "Sepolture Femminili",
            "Statua S.Michele",
            "La Pupa",
            "Specchio da toletta",
            "Cesoie in ferro",
            "Il Trullo",
            "Aratro",
            "Friscoli",
            "Botti",
            "La Mesa",
            "Telaio",
            "Presentosa",
            "Maiolica",
            "Catene De Cesaris",
    };

    String [] descrizioniOggetti = {
            "Sepolture femminili di epoca Neolitica: gli scheletri sono stati ritrovati in posizione fetale con corredi funebri tipicamente femminili, osservando la composizione gli aspetti più importanti sono: la posizione fetale che ci indica la credenza nella vita ultraterrena, l’inumazione che è testimonianza dell’esistenza di una ritualità complessa dei nostri antenati e infine il corredo tombale che ci permette di capire che si tratta di donne. Gli oggetti del corredo sono decorati in ocra rossa e questo indizio ci permette di datarli a 6200 anni fa.",
            "Statua di San Michele arcangelo risalente al XII secolo realizzata in pietra. Le tracce cromatiche residue indicano che inizialmente era colorata. La statua è stata realizzata da scalpellini della pietra bianca della Maiella ed è simbolo della continuità dei luoghi di culto in Abruzzo. San Michele è un’entità guerriera e rappresenta il trionfo del bene sul male perciò nell’iconografia classica rappresenta l'entità più guerriera del cristianesimo. Statue raffiguranti S.Michele sono state spesso ritrovate nelle grotte. Venivano posizionate lì dai pastori, come pegno, prima di partire per la transumanza, una sorta di richiesta di protezione per i viaggi pericolosi.",
            "La Pupa, presente in molte feste abruzzesi è simbolo della persistenza nella contemporaneità di un culto propiziatorio per la fertilità della terra e veniva utilizzata per antichi riti pastorali. Nella contemporaneità viene fatta sfilare, talvolta su dei carri allegorici. A Cappelle sul Tavo, un piccolo comune nel pescarese, c'è una delle sfilate più emblematiche.",
            "Manufatto ligneo, frutto dell'artigianato pastorale, risalente agli anni '30 del '900 realizzato da un artista di Palena, piccolo comune del chietino, di nome Emidio Di Guglielmo, come dono per la nipote in occasione del suo matrimonio.",
            "Cesoie realizzate in un unico pezzo in ferro con meccanismo a molla, utilizzate per la tosatura, erano considerate un attrezzo indispensabile del mondo pastorale.",
            "Ricovero in pietra della Majella realizzato a secco, la tecnica di costruzione utilizzata non prevedeva l'utilizzo di leganti, di conseguenza esso è formato da pietre incastrate tra loro e riprende la forma da preesistenti architetture pugliesi. Esso veniva usato in contesto agro-pastorale come ricovero temporaneo durante i mesi estivi.",
            "Aratro del XIX secolo realizzato in legno e presente in molte fonti iconografiche di epoca neolitica con coppia di buoi aggiogati. È un oggetto che mostra la continuità della tradizione agricola abruzzese dalle epoche preistoriche.",
            "Friscoli: dischi forati in fibra vegetale, utilizzati nei frantoi per la spremitura della pasta d’olive, oggi realizzati con materiali sintetici per questioni igieniche.",
            "Nelle cantine abruzzesi il vino veniva conservato all’interno di botti di capienza variabile costituite da doghe in legno. Nella botte il processo di maturazione del vino, che ne migliora anche la qualità, può durare diversi anni. Le botti in legno di rovere esposte nella sala risalgono alla seconda metà del XIX secolo; sulla parte anteriore sono ben visibili i rubinetti per la spillatura del vino.",
            "La 'Mesa' o 'Madia' è un oggetto dell’artigianato domestico agropastorale utilizzato per far lievitare e conservare il pane, essa è realizzata con elementi in legno inseriti ad incastro ed è smontabile, in modo da poter essere trasportata in eventuali spostamenti.",
            "Il telaio è lo strumento più importante nel processo di realizzazione dei tessuti, principalmente veniva usato dalle donne che, durante il periodo invernale, realizzavano tutti i tessuti necessari alla vita domestica. La tessitura manuale è una delle tradizioni artigianali più antiche d’Abruzzo ed ha resistito fino alla metà del XX secolo.",
            "Ciondolo propiziatorio realizzato in filigrana, è di buon auspicio per l’unione della coppia ed è simbolo di fertilità. Essendo un monile di origine pastorale era realizzato in oro di bassa caratura e rame per abbassare i costi. È uno dei gioielli più antichi e noti dell’oreficeria Abruzzese. Veniva donato da un pastore alla sua sposa prima della partenza per la transumanza. È il pegno di fidanzamento che prelude al matrimonio che sarebbe avvenuto al rientro dalla transumanza. Al centro vi è la rappresentazione di due cuori legati da un crescente lunare, simbolo della fertilità. Inoltre gli astri sono elementi tipici dell’artigianato artistico popolare. Fanno da coronamento all’oggetto i raggi solari il cui numero dipendeva dalle disponibilità economiche del pastore.",
            "Mattone prelevato dalla chiesa di San Donato di Castelli detta “La Cappella Sistina della Maiolica”, così definita da Carlo Levi nel 1936. Costituiva parte del soffitto cinquecentesco della chiesa e rappresenta una donna di profilo, detta “la bella”, in abiti rinascimentali risalente al IV decennio del XVI secolo. È esemplificativa dei canoni di bellezza del Rinascimento. I colori blu cobalto,  giallo,  verde,  marrone e  arancio sono tipici delle maioliche di Castelli.",
            "La catena in ferro era costituita da 16 maglie e lunga circa tre metri e mezzo, il suo peso si aggirava intorno ai 10 Kg. La catena veniva fissata alle gambe dei detenuti attraverso una maniglia a forma di ferro di cavallo assicurata ad un perno. I condannati, legati così in coppia notte e giorno, erano costretti a spostarsi insieme in una posizione da loro stessi definita stare in calzetta. La catena esposta è quella che legò insieme due tra i più illustri detenuti del carcere pescarese, i cugini Antonio e Clemente De Caesaris.",
    };


    public int getNumero_stanze() {
        return numero_stanze;
    }

    public int[] getImmagini() {
        return immagini;
    }

    public String[] getTitoli() { return titoli; }

    public int getImmagine(int position) { return immagini[position]; }

    public int getOggetto(int position) { return oggetti[position]; }

    public String getTitolo(int posizione)
    {
        return titoli[posizione];
    }

    public String[] getDescrizioniStanza() {
        return descrizioniStanza;
    }

    public String getLinkSale(int posizione) {
        return linkSale[posizione];
    }

    public String getDescrizioniOggetti(int position) {return descrizioniOggetti[position]; }

    public String getDescrizioneSala(int posizione){ return descrizioniStanza[posizione]; }

    public String getTitoliOggetti(int position) { return titoliOggetti[position]; }


}

