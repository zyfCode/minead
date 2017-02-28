/*******
 *缺点无法使用form的setValues设置两个值 
 */
/**
 * @name Horn.Comselect
 * @class
 * 下拉选项组件</br>
 * 替代html的select组件，有更加丰富的交互和功能
 */

/**@lends Horn.Combox# */
/**
 * 用于设置图标超链接是否可用，默认false为可用
 * @name seta_clickdisable(a_clickdisable)
 * @type String
 * @default "false"
 * @example
 * 无
 */


Horn.Comselect = Horn.extend(Horn.Select,{
	COMPONENT_CLASS:"Comselect",
	pLabel : "",
	filterFlag : "",
	displayField : null,
	keyAttr : "label",
	pKeyAttr : "pkey",
	valueAttr : "value",
	defaultValue : "",
	defaultText : "",
	showLabel : true,
	a_clickdisable:false,
	a_clicktext:"",
	/**
	 * @ignore
	 */
	init : function(dom) {
		Horn.Comselect.superclass.init.apply(this,arguments) ;
		this.combInit() ;
		//设置可能的多字典切换功能
		var field = this.el.find('input[ref]'),
			keyAttr = this.el.attr('keyfield'),
			pkeyAttr = this.el.attr('pkeyfield'),
			valueAttr = this.el.attr('titlefield')
			;
		if(keyAttr) this.keyAttr = keyAttr;	
		if(pkeyAttr) this.pkeyAttr = pkeyAttr;	
		if(valueAttr) this.valueAttr = valueAttr;


		var dictName = this.params['dictName'];
		if(dictName){
			this.field.attr('ref',dictName.split(',')[0] + (this.multipleline?'_m':'_s') ) ;
		}
		this.displayField = field;
		this.defaultValue = this.params.value || this.hidden.val();
		this.a_clicktext=this.params.a_click;
//		this.setValue(this.defaultValue,true);
		
		//BUG #6838 combo_设置为只读的任然可以选择更改 设置为只读的任然可以选中后按退格键清除
		if(this.params.readonly) {
        	this.setReadonly(true);
        }
		if(this.params.a_clickdisable&&this.params.a_clickdisable==true){
			this.seta_clickdisable(true);
		}
	},
	 seta_clickdisable : function(a_clickdisable) {
	    	if (a_clickdisable === true) {
//	    		var s=this.el.find('.fa.fa-hand-pointer-o.u-autoitem-down');
//	    		alert(s.attr("onclick"));
	    		 this.el.find('.fa.fa-hand-pointer-o.u-autoitem-down').removeAttr("onclick");
	    	} else {
	    		this.el.find('.fa.fa-hand-pointer-o.u-autoitem-down').attr("onclick",this.a_clicktext);
	    	}
	    },
	/**
	 * @ignore
	 */
	combInit : function(){
		var refname = this.hidden.attr("refname");
		if (refname) {
			this.field.bind("change",Horn.Util.apply(this.onCombChange,this));
		}
	},
	/**
	 * @ignore
	 */
	onCombChange : function(e, val){
		var refname = this.hidden.attr("refname") ;
		var refnames = refname.split(";") ;
		for(var i=0;i<refnames.length;i++){
			var rn = refnames[i] ;
			if (rn) {
				var rns = rn.split(",") ;
				Horn.getComp(rns[0],rns[1]).filterByPLabel(this.hidden.val()) ;
			}
		}
	},
	changeDict : function(name){
   		this.clearFliter();
		if((this.field.get(0)).getAttribute("multiple")=="true"){
			name+="_m";
		}else{
			name+="_s";
		}
		this.field.attr("ref",name);
		this.listEl = $(
            "div.hc_checkboxdiv[ref_target='" + name + "']");
   		this.multipleline = this.listEl.attr("multiple_line") == "true";
   		this.setValue("");
	},
	selectFirst : function() {
		var lis = this.listEl.children("ul").children("li[key]");
		var key = "";
		var text = "" ;
		for ( var i = 0; i < lis.length; i++) {
			if ($(lis.get(i)).css("display") != "none") {
				key = $(lis.get(i)).attr("key");
				text = jQuery.trim($(lis.get(i)).text());
				if(key!="") break;
			}
		}
		this.setValue({"key":key,"text":text}, true);
	},
	/**
	 * @ignore
	 */
	getPos : function() {
		return {
			left : 0,
			top : 0
		};
	},
	/**
	 * @ignore
	 */
	hideAllList : function() {
		var listEl = this.listEl ;
		$("div.hc_checkboxdiv").each(function(i, o) {
			if (listEl.get(0) != o) {
				$(o).hide();
				$(o).data("show_name", "");
			}
		});
	},
	/**
	 * @ignore
	 */
	showList : function(inputEl, listEl) {
		var hidden = inputEl.prev() ;
		var filter = hidden.data("filter") ;
		if(filter){
			this[filter.name].apply(this,filter.params) ;
		}
		else{
			this.clearFliter(hidden) ;
		}
		Horn.Combox.superclass.showList.apply(this,arguments) ;
	},
	/**
     * @description 根据关联项目过滤下拉列表
     * @function
	 * @name Horn.Combox#filterByPLabel
	 * @param pLabel 父节点值
	 * @returns void
	 * @ignore
	 */
	filterByPLabel : function(pLabel) {
		if(this.pLabel!=pLabel){
			this.reset(true);
			this.pLabel = pLabel ;
			this.hidden.data("filter" ,{
				name : '_filterByPLabel',
				params : arguments 
			}) ;
		}
	},
	/**
	 * @ignore
	 */
	_filterByPLabel : function(pLabel,noSelectfirst) {
		if (!pLabel) {
			return false;
		}
		var ul = this.listEl.children("ul");
		// 先隐藏所有的li
		ul.children("li[key][pKey!='" + pLabel + "']").css("display",
				"none");
		ul.children("li[key][pKey='" + pLabel + "']").css("display",
				"block");
		//若是自己设置的value值，那么在此设置为原始值。
		if(this.defaultValue){
			this.setValue(this.defaultValue);
		}else if(!(noSelectfirst===false)&&(!this.multipleline)){
			this.selectFirst();
		}else{
			this.setValue('');
		}
	},	
	filter : function(f, flag,triggerChange) {
		var filter = {
			name : "_filter",
			params : arguments 
		} ;
		var oldFilter = this.hidden.data("filter") ;
		if(filter!=oldFilter){
			this.hidden.data("filter" ,filter) ;
			//BUG #6840 combo_filter以及setValue不会触发onchange事件
			if (triggerChange) {
	            this.field.trigger('change', filter);
	        }
		}
		var val =this.hidden.val();
		var _comb = this;
		if(val){
			//查询是否有这个值的显示li存在
			this._filter(f, flag);
			setTimeout(function(){
				$(val.split(',')).each(function(idx,v){
					if (_comb.listEl.children("ul").children(
							"li[key=" + v + "]").css('display') == "none") {
						_comb.reset(true);
					}
				});
			},200);
		}else{
			//查询是否有这个值的显示li存在
			this._filter(f, flag);
			setTimeout(function(){
				_comb.setValue('');
			},200);
		}
		
	},
	/**
	 * @ignore
	 */
	_filter : function(f, flag) {
		flag = !!flag;
		var d1 = "block", d2 = "none";
		if (flag) {
			d1 = "none";
			d2 = "block";
		}
		// 先隐藏所有的li
		var liList = this.listEl.children("ul").children("li[key]") ;
		liList.css("display", d1);
		var D = ",";
		if ($.type(f) == "string") {
			f += D;
		}
		liList.each(
			function(index, dom) {
				var li = $(dom);
				var key = li.attr("key");
				if ($.type(f) == "function") {
					if (f.call(this, key)) {
						li.css("display", d2);
					}
				} else if ($.type(f) == "array") {
					if (jQuery.inArray(key, f) >= 0) {
						li.css("display", d2);
					}
				} else if ($.type(f) == "string") {
					if (f.indexOf(key + D) > -1) {
						li.css("display", d2);
					}
				}
		});
	},
	clearFliter : function() {
		this.listEl.children("ul").children("li[key]").css("display", "block");
		this.hidden.data('filter',null);
	},
	clearList : function() {
		this.reset(true);
		this.listEl.children("ul").children("li[key]").remove();
	},
	addItems : function(data, isClear) {
		this.addDatas(data, isClear);
	},
	/**
     * @description 动态增加下拉列表项目
     * @function
	 * @name Horn.Combox#addDatas
     * @param data {Json or Array} [{label:'3','value':'测试3'},{label:'4','value':'测试4'}]　
     * @param isClear 是否清空原来的列表项
     * @ignore
	 */
	addDatas : function(data, isClear) {
		var list = [], listDiv = this.listEl, keyAttr = this.keyAttr, valueAttr = this.valueAttr, showLabel = this.showLabel;
		var hidden = this.hidden ;
		var _this = this ;
		var eventData = {
				inputEl : this.field,
				listEl : listDiv
		};
		if (jQuery.type(data) == "array") {
			list = data;
		}else if (jQuery.type(data) == "object"){
			if (jQuery.isPlainObject(data)) {
				if (jQuery.isEmptyObject(data)) {
					return;
				}
				list.push(data);
			}
		}else{
			return;
		}
		if (isClear === true) {
			this.clearList();
		}
		if (list.length > 0) {
			var ul = listDiv.children("ul");
			var multipleline = (listDiv.attr("multiple_line") == "true");
			$.each(list, function(index, obj) {
				var key = obj[keyAttr], val = obj[valueAttr];
				if (!key) {
					key = obj["code"];
					val = obj["text"];
				}
				if (key) {
					var li = ul
							.children("li[key='"
									+ key
									+ "']");
					if (li.length == 0) {
						var li = $("<li key='"
								+ key
								+ "' title='"
								+ val
								+ "'></li>");
						var label = $("<label></label>");
						label
								.text((showLabel ? key
										+ ":"
										: "")
										+ val);
						if (multipleline) {
							label.prepend('<input type="checkbox" />');
						}
						li.append(label);
						ul.append(li) ;
						if (listDiv.data("show_name") == hidden.attr("name")) {
							li.bind("click.li", eventData, Horn.Util.apply(
									_this.listClick, _this));
						}
					}
				}
			});
		}
	},
    /**
     * @description 显示错误信息
     * @function
     * @name Horn.Combox#showError
     * @ignore
     */
	showError:function(){
		Horn.Combox.superclass.showError.apply(this,arguments) ;
		 var msg = this.msgDiv;
		 //BUG #6552 【combox】(继承)addRule(rule)(原先无校验规则) 错误提示的位置不正确，偏离的位置相当大
	     //msg.css('margin-top','-40px');
	},
	  /**
     * @description 设置select的值
     * @function
     * @name Horn.Select#setValue
     * @param {String} value
     * @param {Boolean} triggerChange 是否触发值更改事件
     */
    setValue : function(value, triggerChange, notBlur) {
    	
    	this.handerHeadItem();
        var hidden = this.hidden ;
        var oldVal = hidden.val();
        var field = this.field ;
        //BUG #6560 【combox】(继承)clearValue() 不会将组件回复到原始状态
        //if (value === undefined || value === null || value==="") {
        if (value === undefined || value === null) {
        	field.val("");
            hidden.val("");
//            if (!notBlur) {
//                this.field.blur();
//            }
            return false;
        }
        if (value == "") {
    		//BUG #6440 【form】各个表单组件的校验提示不统一
        	hidden.val("");
        	this.tempCheckedKeyArray=[];
    	}
        if($.type(value)=="string"){
            value = {"key":value} ;
        }
        //修改select的设置初始值逻辑，修复在全选条件下分隔符为空时值无法展示的问题
        var ul = this.listEl.children("ul") ;
        if (this.listEl.length > 0) {
            if (this.multipleline) {
            	if(value.key == "" || value.key==null ){
            		field.val("");
            	}else if(value.text){
            		field.val(value.text);
            	}else{
            		var checkedString = [];
            		var tmpKey = [];
            		if(this.delimiter != ""){
            			tmpKey = value.key.split(this.delimiter);
            		}else{
            			tmpKey = value.key.toCharArray();
            		}
            		this.tempCheckedKeyArray=tmpKey;
            		if(this.params.dictName){
            			for(var i=0;i<tmpKey.length;i++){

            				//STORY #9624 [研发中心/内部需求][JresPlus][UI]-数据字典下拉后失丢焦点 再通过setvalue方法设置值，设置不成功 
	            			checkedString.push(Horn.getDict(this.params.dictName)[tmpKey[i]]);
            			}
            		}else{
            			var dataFromList = this.getListData().data;
            			for(var i=0;i<tmpKey.length;i++){
            				for(var j=0;j<dataFromList.length;j++){
            					if(tmpKey[i] == dataFromList[j].key){
            						checkedString.push(dataFromList[j].value);
            					}
            				}
            			}
            		}
            		checkedString=checkedString.toString().replace(/,/g, this.delimiter);
            		field.val(checkedString);
            	}
                hidden.val(value.key);
            } else {
                if(!value.text){
                    var li = ul.children("li[key='" + value.key + "']");
                    value.text = jQuery.trim(li.text());
                	if(!this.showLabel){
                		var span = li.find('span');
                		value.text = value.text.replace(span.text(),"");
                    }
                   
                }
                //BUG #6553 【combox】(继承)setValue() 设置无效值，依然会生效，但是getValue返回为""
                if(value.text!=""){
                    field.val(value.text);
                	hidden.val(value.key);
                }else{
                	//清空操作
                	if(value.key==""){
                        field.val("");
                	}else{//设置不合理值的非法操作
                		return;
                	}

                }
             }
            if (triggerChange && oldVal!=value.key) {
                field.trigger('change', [ value.key ]);
            }
        }
        
        //BUG #6839 【combox】在多选模式下无效的值任然可以设置成功  
        if (this.multipleline) {
        	//重新计算有效值，然后赋值给隐藏域（只针对多选有效）
            var val = this.hidden.val() ;
            var tmpVal = [];    //story 8487
            var _this = this;
            if(val!="" && val!=null){
	            $("input[type='checkbox']", this.listEl).each(function(index, dom) {
	                var li = $(dom).parent().parent("li[key]");
	                var curVal = li.attr("key");
	                var valArray = val.split(_this.delimiter);
	                var len = valArray.length;
	                for(var i=0; i< len; i++){
	                	var key = valArray[i];
	                     if(curVal==key){
	                    	 tmpVal.push(key);
	                     }
	                }
	            });
	            //BUG #6927 【combox】多选模式下，设置"defValue":"1,0"，然后调用reset()方法会造成显示的值为"1,0"但是提交值变成[01,]
	            //tmpVal = tmpVal.substring(0,tmpVal.length-1);
	            this.hidden.val(tmpVal.join(this.delimiter));
            }
        }
        
      //将选中的值保存起来
       this.tempCheckedValue = this.field.val();
        
        //STORY #9468 [研发中心/内部需求][JresPlus][UI]comboxt组件在必填状态下，先清空值，然后执行setValue()设置非空值后，校验信息依然存在。设置了非空值后，校验通过，校
        //STORY #9469 [研发中心/内部需求][JresPlus][UI]comboxt组件在必填状态下，无值的情况下丢失焦点，然后执行setValue()设置非空值后，校验信息依然存在。设置了非空值后， 
        if (!notBlur) {
            this.validate();
        }
        
        
    } 
}) ;
Horn.Field.regFieldType("div.uv_comselect",Horn.Comselect);