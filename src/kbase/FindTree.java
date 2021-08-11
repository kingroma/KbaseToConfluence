package kbase;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import kbase.obj.TreeObj;

// root 
//<li> 
//<div class="plugin_pagetree_childtoggle_container"> <a class="plugin_pagetree_childtoggle" href=" "> <img id="plusminus102605914-" border="0" src="/confluence/images/icons/tree_plus.gif"> </a> 
//</div> 
//<div class="plugin_pagetree_children_content"> <span class="plugin_pagetree_children_span" id="childrenspan102605914-"> <img src="/confluence/s/1814/51/_/images/icons/docs_16.gif" height="16" width="16" border="0" align="absmiddle" title="00. 인수인계문서"> <a href="/confluence/pages/viewpage.action?pageId=102605914">00. 인수인계문서</a> </span> 
//</div> 
//<div id="children102605914-" class="plugin_pagetree_children_container"> 
//</div> </li>

// last 
//<li> 
//<div class="plugin_pagetree_childtoggle_container"> 
// <img border="0" src="/confluence/images/icons/tree_square.gif"> 
//</div> 
//<div class="plugin_pagetree_children_content"> <span class="plugin_pagetree_children_span" id="childrenspan102606034-"> <img src="/confluence/s/1814/51/_/images/icons/docs_16.gif" height="16" width="16" border="0" align="absmiddle" title="현행이슈(19.06.13)"> <a href="/confluence/pages/viewpage.action?pageId=102606034">현행이슈(19.06.13)</a> </span> 
//</div> 
//<div id="children102606034-" class="plugin_pagetree_children_container"> 
// <ul class="plugin_pagetree_children_list" id="child_ul102606034-"></ul> 
//</div> </li>



public class FindTree {
	// https://confluence.alticast.com/rest/api/content/5816986?expand=body
	
	// kbase target 
	// http://kbase.alticast.com/confluence/display/SDP/TBroad
	// root page id 50958401 
	// last child 102605914
	
	private static String JSESSIONID = "32066BF60789C7064B73B4C5EFFB975C" ;
	
	private static String ROOT_PAGE_ID = "50958401";
	 
	public static void main(String[] args) {
//		TreeObj root = new TreeObj();
//		root.setPageId(ROOT_PAGE_ID);
//		find(root);
//		print(root,0);
	}
	
	public static TreeObj getTree() {
		TreeObj root = new TreeObj();
		root.setPageId(ROOT_PAGE_ID);
		find(root);
		// print(root,0);
		return root ; 
	}
	
	private static void find(TreeObj rootTree) {
		String pageId = rootTree.getPageId();
		String url = menuGet(pageId);

		try {
			System.out.println(url);
			Document doc = 
					Jsoup
						.connect(url)
						.cookie("JSESSIONID", JSESSIONID)
						.get();
			
			Elements bodys = doc.getElementsByTag("body");
			
			Element body = bodys.get(0);
			
			Element ul = body.child(0);
			
			Elements lis = ul.children();
			
			Iterator<Element> iter = lis.iterator();
			
			rootTree.setPageId(pageId);
			while ( iter.hasNext() ) {
				Element e = iter.next();
				Element c = e.child(0).child(0) ;
				
				String tagName = c.tagName();
				boolean isLeaf = false ; 
				
				String pId = null ; 
				
				if ( "a".equalsIgnoreCase(tagName) ) {
					isLeaf = true ; 
				} else if ( "img".equalsIgnoreCase(tagName) ) {
					isLeaf = false ; 
				}
				Elements plugin_pagetree_children_containers = e.getElementsByClass("plugin_pagetree_children_container");
				Element ppcc = plugin_pagetree_children_containers.get(0);
				
				pId = ppcc.id();
				pId = pId.replace("children", "").replace("-", "");
				
				TreeObj lv2 = new TreeObj();
				lv2.setPageId(pId);
				
				if ( isLeaf == true ) {
					find(lv2);
				}
				
				rootTree.addChild(lv2);
				// System.out.println(pId);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void print(TreeObj obj, int lv) {
		if ( obj != null ) {
			String tab = "" ; 
			for ( int i = 0 ; i < lv ; i ++ ) {
				tab += "  ";
			}
			System.out.println(tab + obj.getPageId());
			if ( obj.getChild() != null && !obj.getChild().isEmpty() ) {
				for ( TreeObj c : obj.getChild() ) {
					if (c != null) {
						print(c,lv + 1);
					}
				}
			}
		}
	}
	
//	generateRequestString : function(treeId, pageId, ancestors, startDepth, spaceKey) {
//        var reqString = this.treeRequests[treeId];
//
//        if (pageId == "Orphan")
//            reqString += "&hasRoot=false&spaceKey=" + spaceKey;
//        else
//            reqString += "&hasRoot=true&pageId=" + pageId;
//
//        reqString += "&treeId=" + treeId + "&startDepth=" + startDepth;
//
//        jQuery.each(ancestors, function() {
//            reqString += "&ancestors=" + this;
//        });
//
//        return reqString;
//    },
	
	
//	  <input type="hidden" name="treeId" value=""> 
//    <input type="hidden" name="treeRequestId" value="/confluence/plugins/pagetree/naturalchildren.action?decorator=none&amp;excerpt=false&amp;sort=position&amp;reverse=false&amp;disableLinks=false"> 
//    <input type="hidden" name="treePageId" value="50958401"> 
//    <input type="hidden" name="noRoot" value="false"> 
//    <input type="hidden" name="rootPageId" value="48958301"> 
//    <input type="hidden" name="rootPage" value=""> 
//    <input type="hidden" name="startDepth" value="0"> 
//    <input type="hidden" name="spaceKey" value="SDP"> 
//    <input type="hidden" name="i18n-pagetree.loading" value="Loading..."> 
//    <input type="hidden" name="loginUrl" value="/confluence/login.action?os_destination=%2Fdisplay%2FSDP%2FTBroad"> 
//    <fieldset class="hidden"> 
//     <input type="hidden" name="ancestorId" value="50958397"> 
//     <input type="hidden" name="ancestorId" value="48958301"> 
//    </fieldset> 

	private static String menuGet(String pageId) {
		// /confluence/plugins/pagetree/naturalchildren.action?decorator=none&amp;excerpt=false&amp;sort=position&amp;reverse=false&amp;disableLinks=false
		String reqString = "http://kbase.alticast.com/confluence/plugins/pagetree/naturalchildren.action?decorator=none&amp;excerpt=false&amp;sort=position&amp;reverse=false&amp;disableLinks=false";
		reqString = reqString.replaceAll("&amp;", "&");
		
		reqString += "&hasRoot=true&pageId=" + pageId;
		reqString += "&treeId=" + "" + "&startDepth=" + "0";
		
		reqString += "&ancestors=" + 50958397 ;
		reqString += "&ancestors=" + 48958301 ;
		
		return reqString ; 
	}

	public static String getJSESSIONID() {
		return JSESSIONID;
	}

	public static void setJSESSIONID(String jSESSIONID) {
		JSESSIONID = jSESSIONID;
	}

	public static String getROOT_PAGE_ID() {
		return ROOT_PAGE_ID;
	}

	public static void setROOT_PAGE_ID(String rOOT_PAGE_ID) {
		ROOT_PAGE_ID = rOOT_PAGE_ID;
	}
	
	
	
//	50958401
//	  102605914
//	    102606034
//	  76023264
//	    102608028
//	    44798882
//	      98664869
//	      98668265
//	      44802679
//	      44799912
//	      44799703
//	      44800051
//	      44800053
//	      44800177
//	      44800179
//	      103350320
//	      91490235
//	      44800020
//	      96307258
//	      103350315
//	      44800112
//	      46017665
//	      44800101
//	      60164671
//	      44800650
//	      46016816
//	      44799914
//	      44799921
//	      44799694
//	      44799907
//	        102614974
//	      44799935
//	      44799933
//	      44799737
//	      44800152
//	      44800171
//	      44799965
//	      44799799
//	      44799862
//	      44799952
//	      44799937
//	      44799741
//	      44799748
//	      44799733
//	      44804316
//	      46011247
//	      46011244
//	      44800049
//	      44800660
//	      49911483
//	      44803681
//	      46018630
//	      44800644
//	      44800073
//	      44810494
//	      44800077
//	      44800064
//	      46017536
//	      104203470
//	      44800012
//	      44800007
//	      44800044
//	      44800039
//	      44800027
//	      44800036
//	      44800029
//	      104202472
//	      46015243
//	      48373011
//	      102204462
//	      46020064
//	      44800146
//	      44800642
//	      44806554
//	      44800130
//	      44800122
//	      44800126
//	      44800155
//	      46011004
//	      47488043
//	      44800169
//	      44800173
//	      55345735
//	      102613534
//	      102612506
//	      102612518
//	      102612541
//	      102612548
//	      44803370
//	      46013409
//	      44799871
//	      44799961
//	      44799814
//	      44799790
//	      46013686
//	      44799841
//	      44799834
//	      44799823
//	      44799888
//	      44799845
//	      44799948
//	      44799926
//	      44800656
//	      56689350
//	      44799712
//	      44799739
//	      44799735
//	      46013820
//	      46013787
//	      44799692
//	      44799679
//	      47944152
//	      44799701
//	      49909935
//	      103350900
//	      44800149
//	      44800140
//	      102612500
//	      102612498
//	      102612495
//	      102612515
//	      102612513
//	      44799882
//	      104203461
//	      44799963
//	      44799794
//	      44799809
//	      46015736
//	      44799864
//	      44799843
//	      44799828
//	      44806334
//	      44800652
//	      44799847
//	      44799950
//	      44799931
//	    47944144
//	      47944190
//	      47944376
//	      47944389
//	      47944273
//	      47944227
//	      47944205
//	      47944249
//	      47944207
//	      47944266
//	      47944192
//	      47944187
//	      47944184
//	      47944387
//	      47944352
//	      47944351
//	      47944297
//	      47944328
//	      47944299
//	      47944296
//	      47944301
//	      47944295
//	      47944360
//	      47944358
//	      47944356
//	      47944355
//	      47944359
//	      47944357
//	      47944354
//	      47944395
//	      47944279
//	      47944399
//	      47944397
//	      47944398
//	      47944285
//	      47944284
//	      47944292
//	      47944291
//	      47944288
//	      47944290
//	      47944289
//	      47944287
//	      47944385
//	      47944231
//	      47944366
//	      47944373
//	      47944377
//	      47944369
//	      47944371
//	      47944367
//	      47944368
//	      47944379
//	      47944382
//	      47944365
//	      47944370
//	      47944364
//	      47944283
//	      47944388
//	      47944390
//	      47944394
//	      47944281
//	      47944213
//	      47944212
//	      47944271
//	      47944230
//	      47944223
//	      47944219
//	      47944204
//	      47944236
//	      47944234
//	      47944253
//	      47944252
//	      47944210
//	      47944206
//	      47944255
//	      47944260
//	      47944257
//	      47944186
//	      47944191
//	      47944188
//	      47944202
//	      47944200
//	      47944175
//	      47944173
//	      47944159
//	      47944177
//	      47944374
//	      47944372
//	      47944272
//	      47944225
//	      47944228
//	      47944232
//	      47944238
//	      47944235
//	      47944220
//	      47944247
//	      47944268
//	      47944262
//	      47944264
//	      47944263
//	    96306605
//	    76023246
//	    96306941
//	      96307206
//	      96307200
//	      96307252
//	      96307248
//	      96306946
//	      96306944
//	      96307220
//	      96307211
//	    99390094
//	      99390097
//	    103350883
//	  35817076
//	    39292491
//	    40468714
//	      40468770
//	      44806039
//	      40468740
//	      40468841
//	      41420773
//	      46020939
//	      57606227
//	      40468746
//	      44806375
//	      49909767
//	      60754839
//	      60173115
//	      60171836
//	      44805982
//	      46010389
//	      60173542
//	    44804466
//	      44807156
//	      44804473
//	      44804481
//	    49417649
//	    49418400
//	    49418405
//	      49909535
//	    49418410
//	    50432799
//	    50432949
//	      42928957
//	      42929312
//	      44173152
//	      44796976
//	      44798487
//	      44798744
//	        101187610
//	      44799132
//	      46011165
//	      50432626
//	        50954282
//	        50954290
//	        50433537
//	      50433058
//	      57082668
//	      61181962
//	      64652864
//	      93618500
//	      102596742
//	      102612654
//	      102205429
//	        93618469
//	        93061954
//	        98666942
//	  91489596
//	  76023272
//	    76023274
//	    46018620
//	      47483641
//	      47483672
//	      47483675
//	      47483678
//	      47947577
//	      47947579
//	      49415628
//	      49415630
//	      63701015
//	      74974811
//	      76023214
//	    59316507
//	      60163907
//	        60163912
//	        60163914
//	        60169298
//	        60163934
//	        60163932
//	        60163936
//	      60163894
//	        66617502
//	      60166889
//	      59316510
//	        59316519
//	        59316529
//	        59316540
//	        59316542
//	        59801700
//	    91489523
//	      96306864
//	      91491085
//	      93061714
//	        96305218
//	        91491089
//	        93061742
//	        96736313
//	      91488593
//	        98665017
//	          102604522
//	          102599328
//	        91489109
//	        91489090
//	        91489127
//	        97320984
//	          98667810
//	          98667812
//	        98041880
//	        99385959
//	        93619916
//	      94080757
//	      91914396
//	        96305234
//	        96731844
//	        93061873
//	        96305885
//	        93061756
//	        96305902
//	        93061760
//	        98665765
//	        93061733
//	        96306487
//	        98665756
//	      91489494
//	        96306914
//	      102602829
//	        102599642
//	    67568002
//	      67863918
//	        70228717
//	        70975836
//	          71471375
//	          71471421
//	          71471440
//	          71471464
//	        71471380
//	          71471413
//	          71471426
//	      68298500
//	        96736663
//	        69796306
//	      68298521
//	      68298950
//	        67108959
//	        69271790
//	      68298952
//	        68294167
//	        68299064
//	        68299066
//	        68300885
//	        68300859
//	        68300864
//	        68300866
//	        68300873
//	        68300880
//	        68301789
//	        68301792
//	        68301794
//	        69272871
//	        69272959
//	        69795981
//	        69795992
//	        68300877
//	      68297267
//	        67863895
//	        67863899
//	        69273054
//	        68299178
//	        68297347
//	      69273569
//	      70228698
//	      78939658
//	      68299131
//	    102600007
//	      102600009
//	        102600011
//	      102600027
//	  60754551
//	    60169142
//	      60170565
//	      60169147
//	    60175935
//	      60754778
//	      60176860
//	      60176813
//	      61178617
//	      60176869
//	    68291827
//	      68292765
//	        68295391
//	      68292776
//	      68292781
//	      68292792
//	      68291524
//	    85951585
//	    102204009
//	      102204102
//	      102204081
//	      102204351
//	      102204349
//	      102204053
//	    91488585
//	      91488591
//	        91490023
//	          91490738
//	          96305521
//	          91490405
//	          91490254
//	          96305495
//	          99386930
//	          91490900
//	          91490885
//	            91490641
//	          91490256
//	          94077015
//	            98665145
//	            96737003
//	            97845273
//	            96737000
//	            96737007
//	            96737005
//	            91490906
//	            91490908
//	            91490913
//	            98664454
//	            93618769
//	            93618772
//	            92602687
//	            92602683
//	            92602685
//	        96306877
//	        91489180
//	          95158792
//	          91489218
//	            94079136
//	              94079367
//	              94079425
//	            94079334
//	              91488956
//	              91489545
//	              91488869
//	              91490021
//	          91489793
//	          91489787
//	          91489532
//	          91489511
//	          91489714
//	          96307265
//	            91489491
//	            91489725
//	            91489789
//	            94081022
//	            91489727
//	            96305387
//	            96305389
//	            96305426
//	            96305422
//	            93619783
//	            97845329
//	            98205799
//	            98668985
//	            93618592
//	            91489474
//	            91489183
//	            91489421
//	            91489750
//	          98665236
//	          96305585
//	          93619786
//	          91490326
//	        98667911
//	        91489325
//	          91489748
//	          96735420
//	          96735534
//	          96735194
//	          91489723
//	          91491047
//	            98666105
//	          91489746
//	          91489416
//	          94079376
//	          91490014
//	            91489327
//	            91489701
//	            91489712
//	            91489753
//	          91490264
//	          91489721
//	          91489755
//	        96307309
//	        91489303
//	          94798067
//	          91489429
//	          94077719
//	          98668778
//	            96306883
//	            91489312
//	            91489331
//	            91489365
//	            91489371
//	            91489374
//	            91489534
//	            91489379
//	            91489707
//	            91490007
//	            91489382
//	            91489386
//	            91489406
//	            91489409
//	            91489411
//	            91489425
//	            91489427
//	            91489431
//	            91490328
//	            98669083
//	            96307303
//	            94798133
//	            91489335
//	        91489223
//	          91489285
//	          91489703
//	          91489757
//	          96307305
//	            94078424
//	          92602663
//	        91489933
//	        91489178
//	          91489599
//	        96306743
//	        98668784
//	        91914630
//	        96732481
//	      91489935
//	        91489941
//	          91490809
//	        94077948
//	        93619665
//	        96307191
//	        94080832
//	        99254315
//	      95584491
//	      96306859
//	      96307299
//	        99254303
//	        102205294
//	        102596795
//	        102596811
//	      96734902
//	      102204124
//	      102598209
//	        102598266
//	      102596845
//	      102600085
//	        99389714
//	        102600078
//	      102596857
//	      98667676
//	      102596853
//	      96305948
//	      100663411
//	      93061146
//	      99386959
//	    102614965
//	  55803926
//	    76028773
//	    85066107
//	    96731508
//	    78938366
//	    90702633
//	    82707300
//	    68878547
//	    82706822
//	    80643081
//	    82509911
//	    82509915
//	    57082938
//	    76036002
//	      94797996
//	      94798001
//	        103841798
//	      96731513
//	      102605907
//	    55803928
//	  70975841
//	    70975845
//	    70975848
//	    70975850
//	    70979559
//	    70979633
//	    70979637
//	    82707677
//	    84018561
//	    76031778
//	  81166490
//	    87163328
//	      85066084
//	      85066086
//	      85066088
//	      86639892
//	      86639894
//	      87163326
//	    87163330
//	      102601018
//	    102601012
//	      102601015
//	  42926508
//	    42926511
//	      44802401
//	    42928696
//	    42929222
//	    44800881
//	    44801220
//	    44802414
//	      44802418
//	    55803939
//	    46012853
//	    46013502
//	    68293419
//	    44794620
//	    82118040
//	    46012419
//	    47946765
//	    44176992
//	    44173198
//	    74154174
//	    44795121
//	    47482139
//	    47481881
//	    44174306
//	    44795332
//	    48367868
//	    45515380
//	    46011791
//	    46008762
//	    47487940
//	    44797275
//	    58426448
//	    69272109
//	    50956186
//	  60175296
//	    70224627
//	    68295386
//	    102598999
//	    70229081
//	    70978806
//	    68295384
//	    76037186
//	    70223915
//	    70979853
//	  102601071
//	    102601082
//	  78152154
//	  67568673
//	    70976942
//	    70976944
//	    70976946
//	    70976948
//	  47487417
//	    47487420
//	    68300199
//	    84018517
//	    86640530
//	    95158712
//	    102600841
//	    102603160
//	    70228372
//	    69796855

	
	
}
