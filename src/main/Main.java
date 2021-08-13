package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import confluence.CreateContent;
import kbase.FindDoc;
import kbase.obj.DocObj;

public class Main {
	// https://docs.atlassian.com/atlassian-confluence/REST/6.6.0/
	// https://confluence.alticast.com/rest/api/content/5816986?expand=body
	public static void main(String[] args) {
		readKbaseTreeFile();
		
		System.exit(1);
		Integer kbasePageId = 0 ; // 98664869 ;
		
		Integer conflunenceParentId =  47060149 ;
		
		Integer[] arr = {
				47944394
		} ;
		
		for ( int i = 0 ; i < arr.length ; i ++ ) {
			kbasePageId = arr[i];
			DocObj docObj = FindDoc.find(kbasePageId);
			
			Map<String,Object> map = CreateContent.create("" + docObj.getTitle() + "" ,conflunenceParentId,docObj.getBody());
			
			System.out.println("      "+map.get("id"));
		}
		 
	}
	
	public static void readKbaseTreeFile() {
		System.out.println(SDP_MENU.split("\n").length);
		String[] split = SDP_MENU.split("\n");
		
		for ( String s : split ) {
			String spaces = s.replaceAll("[0-9]*", "");
		} 
	}
	
	private static final String SDP_MENU = 
			"50958401\n"
			+ "  102605914\n"
			+ "    102606034\n"
			+ "  76023264\n"
			+ "    102608028\n"
			+ "    44798882\n"
			+ "      98664869\n"
			+ "      98668265\n"
			+ "      44802679\n"
			+ "      44799912\n"
			+ "      44799703\n"
			+ "      44800051\n"
			+ "      44800053\n"
			+ "      44800177\n"
			+ "      44800179\n"
			+ "      103350320\n"
			+ "      91490235\n"
			+ "      44800020\n"
			+ "      96307258\n"
			+ "      103350315\n"
			+ "      44800112\n"
			+ "      46017665\n"
			+ "      44800101\n"
			+ "      60164671\n"
			+ "      44800650\n"
			+ "      46016816\n"
			+ "      44799914\n"
			+ "      44799921\n"
			+ "      44799694\n"
			+ "      44799907\n"
			+ "        102614974\n"
			+ "      44799935\n"
			+ "      44799933\n"
			+ "      44799737\n"
			+ "      44800152\n"
			+ "      44800171\n"
			+ "      44799965\n"
			+ "      44799799\n"
			+ "      44799862\n"
			+ "      44799952\n"
			+ "      44799937\n"
			+ "      44799741\n"
			+ "      44799748\n"
			+ "      44799733\n"
			+ "      44804316\n"
			+ "      46011247\n"
			+ "      46011244\n"
			+ "      44800049\n"
			+ "      44800660\n"
			+ "      49911483\n"
			+ "      44803681\n"
			+ "      46018630\n"
			+ "      44800644\n"
			+ "      44800073\n"
			+ "      44810494\n"
			+ "      44800077\n"
			+ "      44800064\n"
			+ "      46017536\n"
			+ "      104203470\n"
			+ "      44800012\n"
			+ "      44800007\n"
			+ "      44800044\n"
			+ "      44800039\n"
			+ "      44800027\n"
			+ "      44800036\n"
			+ "      44800029\n"
			+ "      104202472\n"
			+ "      46015243\n"
			+ "      48373011\n"
			+ "      102204462\n"
			+ "      46020064\n"
			+ "      44800146\n"
			+ "      44800642\n"
			+ "      44806554\n"
			+ "      44800130\n"
			+ "      44800122\n"
			+ "      44800126\n"
			+ "      44800155\n"
			+ "      46011004\n"
			+ "      47488043\n"
			+ "      44800169\n"
			+ "      44800173\n"
			+ "      55345735\n"
			+ "      102613534\n"
			+ "      102612506\n"
			+ "      102612518\n"
			+ "      102612541\n"
			+ "      102612548\n"
			+ "      44803370\n"
			+ "      46013409\n"
			+ "      44799871\n"
			+ "      44799961\n"
			+ "      44799814\n"
			+ "      44799790\n"
			+ "      46013686\n"
			+ "      44799841\n"
			+ "      44799834\n"
			+ "      44799823\n"
			+ "      44799888\n"
			+ "      44799845\n"
			+ "      44799948\n"
			+ "      44799926\n"
			+ "      44800656\n"
			+ "      56689350\n"
			+ "      44799712\n"
			+ "      44799739\n"
			+ "      44799735\n"
			+ "      46013820\n"
			+ "      46013787\n"
			+ "      44799692\n"
			+ "      44799679\n"
			+ "      47944152\n"
			+ "      44799701\n"
			+ "      49909935\n"
			+ "      103350900\n"
			+ "      44800149\n"
			+ "      44800140\n"
			+ "      102612500\n"
			+ "      102612498\n"
			+ "      102612495\n"
			+ "      102612515\n"
			+ "      102612513\n"
			+ "      44799882\n"
			+ "      104203461\n"
			+ "      44799963\n"
			+ "      44799794\n"
			+ "      44799809\n"
			+ "      46015736\n"
			+ "      44799864\n"
			+ "      44799843\n"
			+ "      44799828\n"
			+ "      44806334\n"
			+ "      44800652\n"
			+ "      44799847\n"
			+ "      44799950\n"
			+ "      44799931\n"
			+ "    47944144\n"
			+ "      47944190\n"
			+ "      47944376\n"
			+ "      47944389\n"
			+ "      47944273\n"
			+ "      47944227\n"
			+ "      47944205\n"
			+ "      47944249\n"
			+ "      47944207\n"
			+ "      47944266\n"
			+ "      47944192\n"
			+ "      47944187\n"
			+ "      47944184\n"
			+ "      47944387\n"
			+ "      47944352\n"
			+ "      47944351\n"
			+ "      47944297\n"
			+ "      47944328\n"
			+ "      47944299\n"
			+ "      47944296\n"
			+ "      47944301\n"
			+ "      47944295\n"
			+ "      47944360\n"
			+ "      47944358\n"
			+ "      47944356\n"
			+ "      47944355\n"
			+ "      47944359\n"
			+ "      47944357\n"
			+ "      47944354\n"
			+ "      47944395\n"
			+ "      47944279\n"
			+ "      47944399\n"
			+ "      47944397\n"
			+ "      47944398\n"
			+ "      47944285\n"
			+ "      47944284\n"
			+ "      47944292\n"
			+ "      47944291\n"
			+ "      47944288\n"
			+ "      47944290\n"
			+ "      47944289\n"
			+ "      47944287\n"
			+ "      47944385\n"
			+ "      47944231\n"
			+ "      47944366\n"
			+ "      47944373\n"
			+ "      47944377\n"
			+ "      47944369\n"
			+ "      47944371\n"
			+ "      47944367\n"
			+ "      47944368\n"
			+ "      47944379\n"
			+ "      47944382\n"
			+ "      47944365\n"
			+ "      47944370\n"
			+ "      47944364\n"
			+ "      47944283\n"
			+ "      47944388\n"
			+ "      47944390\n"
			+ "      47944394\n"
			+ "      47944281\n"
			+ "      47944213\n"
			+ "      47944212\n"
			+ "      47944271\n"
			+ "      47944230\n"
			+ "      47944223\n"
			+ "      47944219\n"
			+ "      47944204\n"
			+ "      47944236\n"
			+ "      47944234\n"
			+ "      47944253\n"
			+ "      47944252\n"
			+ "      47944210\n"
			+ "      47944206\n"
			+ "      47944255\n"
			+ "      47944260\n"
			+ "      47944257\n"
			+ "      47944186\n"
			+ "      47944191\n"
			+ "      47944188\n"
			+ "      47944202\n"
			+ "      47944200\n"
			+ "      47944175\n"
			+ "      47944173\n"
			+ "      47944159\n"
			+ "      47944177\n"
			+ "      47944374\n"
			+ "      47944372\n"
			+ "      47944272\n"
			+ "      47944225\n"
			+ "      47944228\n"
			+ "      47944232\n"
			+ "      47944238\n"
			+ "      47944235\n"
			+ "      47944220\n"
			+ "      47944247\n"
			+ "      47944268\n"
			+ "      47944262\n"
			+ "      47944264\n"
			+ "      47944263\n"
			+ "    96306605\n"
			+ "    76023246\n"
			+ "    96306941\n"
			+ "      96307206\n"
			+ "      96307200\n"
			+ "      96307252\n"
			+ "      96307248\n"
			+ "      96306946\n"
			+ "      96306944\n"
			+ "      96307220\n"
			+ "      96307211\n"
			+ "    99390094\n"
			+ "      99390097\n"
			+ "    103350883\n"
			+ "  35817076\n"
			+ "    39292491\n"
			+ "    40468714\n"
			+ "      40468770\n"
			+ "      44806039\n"
			+ "      40468740\n"
			+ "      40468841\n"
			+ "      41420773\n"
			+ "      46020939\n"
			+ "      57606227\n"
			+ "      40468746\n"
			+ "      44806375\n"
			+ "      49909767\n"
			+ "      60754839\n"
			+ "      60173115\n"
			+ "      60171836\n"
			+ "      44805982\n"
			+ "      46010389\n"
			+ "      60173542\n"
			+ "    44804466\n"
			+ "      44807156\n"
			+ "      44804473\n"
			+ "      44804481\n"
			+ "    49417649\n"
			+ "    49418400\n"
			+ "    49418405\n"
			+ "      49909535\n"
			+ "    49418410\n"
			+ "    50432799\n"
			+ "    50432949\n"
			+ "      42928957\n"
			+ "      42929312\n"
			+ "      44173152\n"
			+ "      44796976\n"
			+ "      44798487\n"
			+ "      44798744\n"
			+ "        101187610\n"
			+ "      44799132\n"
			+ "      46011165\n"
			+ "      50432626\n"
			+ "        50954282\n"
			+ "        50954290\n"
			+ "        50433537\n"
			+ "      50433058\n"
			+ "      57082668\n"
			+ "      61181962\n"
			+ "      64652864\n"
			+ "      93618500\n"
			+ "      102596742\n"
			+ "      102612654\n"
			+ "      102205429\n"
			+ "        93618469\n"
			+ "        93061954\n"
			+ "        98666942\n"
			+ "  91489596\n"
			+ "  76023272\n"
			+ "    76023274\n"
			+ "    46018620\n"
			+ "      47483641\n"
			+ "      47483672\n"
			+ "      47483675\n"
			+ "      47483678\n"
			+ "      47947577\n"
			+ "      47947579\n"
			+ "      49415628\n"
			+ "      49415630\n"
			+ "      63701015\n"
			+ "      74974811\n"
			+ "      76023214\n"
			+ "    59316507\n"
			+ "      60163907\n"
			+ "        60163912\n"
			+ "        60163914\n"
			+ "        60169298\n"
			+ "        60163934\n"
			+ "        60163932\n"
			+ "        60163936\n"
			+ "      60163894\n"
			+ "        66617502\n"
			+ "      60166889\n"
			+ "      59316510\n"
			+ "        59316519\n"
			+ "        59316529\n"
			+ "        59316540\n"
			+ "        59316542\n"
			+ "        59801700\n"
			+ "    91489523\n"
			+ "      96306864\n"
			+ "      91491085\n"
			+ "      93061714\n"
			+ "        96305218\n"
			+ "        91491089\n"
			+ "        93061742\n"
			+ "        96736313\n"
			+ "      91488593\n"
			+ "        98665017\n"
			+ "          102604522\n"
			+ "          102599328\n"
			+ "        91489109\n"
			+ "        91489090\n"
			+ "        91489127\n"
			+ "        97320984\n"
			+ "          98667810\n"
			+ "          98667812\n"
			+ "        98041880\n"
			+ "        99385959\n"
			+ "        93619916\n"
			+ "      94080757\n"
			+ "      91914396\n"
			+ "        96305234\n"
			+ "        96731844\n"
			+ "        93061873\n"
			+ "        96305885\n"
			+ "        93061756\n"
			+ "        96305902\n"
			+ "        93061760\n"
			+ "        98665765\n"
			+ "        93061733\n"
			+ "        96306487\n"
			+ "        98665756\n"
			+ "      91489494\n"
			+ "        96306914\n"
			+ "      102602829\n"
			+ "        102599642\n"
			+ "    67568002\n"
			+ "      67863918\n"
			+ "        70228717\n"
			+ "        70975836\n"
			+ "          71471375\n"
			+ "          71471421\n"
			+ "          71471440\n"
			+ "          71471464\n"
			+ "        71471380\n"
			+ "          71471413\n"
			+ "          71471426\n"
			+ "      68298500\n"
			+ "        96736663\n"
			+ "        69796306\n"
			+ "      68298521\n"
			+ "      68298950\n"
			+ "        67108959\n"
			+ "        69271790\n"
			+ "      68298952\n"
			+ "        68294167\n"
			+ "        68299064\n"
			+ "        68299066\n"
			+ "        68300885\n"
			+ "        68300859\n"
			+ "        68300864\n"
			+ "        68300866\n"
			+ "        68300873\n"
			+ "        68300880\n"
			+ "        68301789\n"
			+ "        68301792\n"
			+ "        68301794\n"
			+ "        69272871\n"
			+ "        69272959\n"
			+ "        69795981\n"
			+ "        69795992\n"
			+ "        68300877\n"
			+ "      68297267\n"
			+ "        67863895\n"
			+ "        67863899\n"
			+ "        69273054\n"
			+ "        68299178\n"
			+ "        68297347\n"
			+ "      69273569\n"
			+ "      70228698\n"
			+ "      78939658\n"
			+ "      68299131\n"
			+ "    102600007\n"
			+ "      102600009\n"
			+ "        102600011\n"
			+ "      102600027\n"
			+ "  60754551\n"
			+ "    60169142\n"
			+ "      60170565\n"
			+ "      60169147\n"
			+ "    60175935\n"
			+ "      60754778\n"
			+ "      60176860\n"
			+ "      60176813\n"
			+ "      61178617\n"
			+ "      60176869\n"
			+ "    68291827\n"
			+ "      68292765\n"
			+ "        68295391\n"
			+ "      68292776\n"
			+ "      68292781\n"
			+ "      68292792\n"
			+ "      68291524\n"
			+ "    85951585\n"
			+ "    102204009\n"
			+ "      102204102\n"
			+ "      102204081\n"
			+ "      102204351\n"
			+ "      102204349\n"
			+ "      102204053\n"
			+ "    91488585\n"
			+ "      91488591\n"
			+ "        91490023\n"
			+ "          91490738\n"
			+ "          96305521\n"
			+ "          91490405\n"
			+ "          91490254\n"
			+ "          96305495\n"
			+ "          99386930\n"
			+ "          91490900\n"
			+ "          91490885\n"
			+ "            91490641\n"
			+ "          91490256\n"
			+ "          94077015\n"
			+ "            98665145\n"
			+ "            96737003\n"
			+ "            97845273\n"
			+ "            96737000\n"
			+ "            96737007\n"
			+ "            96737005\n"
			+ "            91490906\n"
			+ "            91490908\n"
			+ "            91490913\n"
			+ "            98664454\n"
			+ "            93618769\n"
			+ "            93618772\n"
			+ "            92602687\n"
			+ "            92602683\n"
			+ "            92602685\n"
			+ "        96306877\n"
			+ "        91489180\n"
			+ "          95158792\n"
			+ "          91489218\n"
			+ "            94079136\n"
			+ "              94079367\n"
			+ "              94079425\n"
			+ "            94079334\n"
			+ "              91488956\n"
			+ "              91489545\n"
			+ "              91488869\n"
			+ "              91490021\n"
			+ "          91489793\n"
			+ "          91489787\n"
			+ "          91489532\n"
			+ "          91489511\n"
			+ "          91489714\n"
			+ "          96307265\n"
			+ "            91489491\n"
			+ "            91489725\n"
			+ "            91489789\n"
			+ "            94081022\n"
			+ "            91489727\n"
			+ "            96305387\n"
			+ "            96305389\n"
			+ "            96305426\n"
			+ "            96305422\n"
			+ "            93619783\n"
			+ "            97845329\n"
			+ "            98205799\n"
			+ "            98668985\n"
			+ "            93618592\n"
			+ "            91489474\n"
			+ "            91489183\n"
			+ "            91489421\n"
			+ "            91489750\n"
			+ "          98665236\n"
			+ "          96305585\n"
			+ "          93619786\n"
			+ "          91490326\n"
			+ "        98667911\n"
			+ "        91489325\n"
			+ "          91489748\n"
			+ "          96735420\n"
			+ "          96735534\n"
			+ "          96735194\n"
			+ "          91489723\n"
			+ "          91491047\n"
			+ "            98666105\n"
			+ "          91489746\n"
			+ "          91489416\n"
			+ "          94079376\n"
			+ "          91490014\n"
			+ "            91489327\n"
			+ "            91489701\n"
			+ "            91489712\n"
			+ "            91489753\n"
			+ "          91490264\n"
			+ "          91489721\n"
			+ "          91489755\n"
			+ "        96307309\n"
			+ "        91489303\n"
			+ "          94798067\n"
			+ "          91489429\n"
			+ "          94077719\n"
			+ "          98668778\n"
			+ "            96306883\n"
			+ "            91489312\n"
			+ "            91489331\n"
			+ "            91489365\n"
			+ "            91489371\n"
			+ "            91489374\n"
			+ "            91489534\n"
			+ "            91489379\n"
			+ "            91489707\n"
			+ "            91490007\n"
			+ "            91489382\n"
			+ "            91489386\n"
			+ "            91489406\n"
			+ "            91489409\n"
			+ "            91489411\n"
			+ "            91489425\n"
			+ "            91489427\n"
			+ "            91489431\n"
			+ "            91490328\n"
			+ "            98669083\n"
			+ "            96307303\n"
			+ "            94798133\n"
			+ "            91489335\n"
			+ "        91489223\n"
			+ "          91489285\n"
			+ "          91489703\n"
			+ "          91489757\n"
			+ "          96307305\n"
			+ "            94078424\n"
			+ "          92602663\n"
			+ "        91489933\n"
			+ "        91489178\n"
			+ "          91489599\n"
			+ "        96306743\n"
			+ "        98668784\n"
			+ "        91914630\n"
			+ "        96732481\n"
			+ "      91489935\n"
			+ "        91489941\n"
			+ "          91490809\n"
			+ "        94077948\n"
			+ "        93619665\n"
			+ "        96307191\n"
			+ "        94080832\n"
			+ "        99254315\n"
			+ "      95584491\n"
			+ "      96306859\n"
			+ "      96307299\n"
			+ "        99254303\n"
			+ "        102205294\n"
			+ "        102596795\n"
			+ "        102596811\n"
			+ "      96734902\n"
			+ "      102204124\n"
			+ "      102598209\n"
			+ "        102598266\n"
			+ "      102596845\n"
			+ "      102600085\n"
			+ "        99389714\n"
			+ "        102600078\n"
			+ "      102596857\n"
			+ "      98667676\n"
			+ "      102596853\n"
			+ "      96305948\n"
			+ "      100663411\n"
			+ "      93061146\n"
			+ "      99386959\n"
			+ "    102614965\n"
			+ "  55803926\n"
			+ "    76028773\n"
			+ "    85066107\n"
			+ "    96731508\n"
			+ "    78938366\n"
			+ "    90702633\n"
			+ "    82707300\n"
			+ "    68878547\n"
			+ "    82706822\n"
			+ "    80643081\n"
			+ "    82509911\n"
			+ "    82509915\n"
			+ "    57082938\n"
			+ "    76036002\n"
			+ "      94797996\n"
			+ "      94798001\n"
			+ "        103841798\n"
			+ "      96731513\n"
			+ "      102605907\n"
			+ "    55803928\n"
			+ "  70975841\n"
			+ "    70975845\n"
			+ "    70975848\n"
			+ "    70975850\n"
			+ "    70979559\n"
			+ "    70979633\n"
			+ "    70979637\n"
			+ "    82707677\n"
			+ "    84018561\n"
			+ "    76031778\n"
			+ "  81166490\n"
			+ "    87163328\n"
			+ "      85066084\n"
			+ "      85066086\n"
			+ "      85066088\n"
			+ "      86639892\n"
			+ "      86639894\n"
			+ "      87163326\n"
			+ "    87163330\n"
			+ "      102601018\n"
			+ "    102601012\n"
			+ "      102601015\n"
			+ "  42926508\n"
			+ "    42926511\n"
			+ "      44802401\n"
			+ "    42928696\n"
			+ "    42929222\n"
			+ "    44800881\n"
			+ "    44801220\n"
			+ "    44802414\n"
			+ "      44802418\n"
			+ "    55803939\n"
			+ "    46012853\n"
			+ "    46013502\n"
			+ "    68293419\n"
			+ "    44794620\n"
			+ "    82118040\n"
			+ "    46012419\n"
			+ "    47946765\n"
			+ "    44176992\n"
			+ "    44173198\n"
			+ "    74154174\n"
			+ "    44795121\n"
			+ "    47482139\n"
			+ "    47481881\n"
			+ "    44174306\n"
			+ "    44795332\n"
			+ "    48367868\n"
			+ "    45515380\n"
			+ "    46011791\n"
			+ "    46008762\n"
			+ "    47487940\n"
			+ "    44797275\n"
			+ "    58426448\n"
			+ "    69272109\n"
			+ "    50956186\n"
			+ "  60175296\n"
			+ "    70224627\n"
			+ "    68295386\n"
			+ "    102598999\n"
			+ "    70229081\n"
			+ "    70978806\n"
			+ "    68295384\n"
			+ "    76037186\n"
			+ "    70223915\n"
			+ "    70979853\n"
			+ "  102601071\n"
			+ "    102601082\n"
			+ "  78152154\n"
			+ "  67568673\n"
			+ "    70976942\n"
			+ "    70976944\n"
			+ "    70976946\n"
			+ "    70976948\n"
			+ "  47487417\n"
			+ "    47487420\n"
			+ "    68300199\n"
			+ "    84018517\n"
			+ "    86640530\n"
			+ "    95158712\n"
			+ "    102600841\n"
			+ "    102603160\n"
			+ "    70228372\n"
			+ "    69796855\n"
			+ "";
}
