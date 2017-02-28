/*******
 *缺点无法使用form的setValues设置两个值 
 */



/**
 * @name Horn.Combox
 * @class
 * 下拉选项组件</br>
 * 替代html的select组件，有更加丰富的交互和功能
 */

/**@lends Horn.Combox# */
/**
 * 用于渲染列表中显示项目的实际值使用的item属性,默认为'text'，非必须，单值不能为空
 * @name Horn.Combox#<b>textField</b>
 * @type String
 * @default "text"
 * @example
 * 无
 */

/**
 * 组件的所属组，可以对相同组内的元素进行约束检查
 * @name Horn.Combox#group
 * @type String
 * @default ""
 * @ignore
 * @example
 * 验证指定对象$obj(scope)中组名为groupname的元素有有效性
 * Horn.Validate.validateAreaByGroup($obj,groupname)
 */


/**
 * 设置是否只读，设置为只读方式的组件，不可以编辑，可以通过下拉选择改变值，可以通过setValue、reset等API修改表单的值，并且可以参与表单提交<font color=red>(注！readonly属性仅对多选的情况下有效，单选无效)</font>
 * @function
 * @name Horn.Combox#setReadonly
 * @param {Boolean} readonly true表示只读，false表示正常
 */

/**
 * 设置表单的值
 * @function
 * @name Horn.Combox#setValue（value,triggerChange,type, notBlur）
 * @param {String} value 值
 * @param {Boolean} triggerChange 是否触发值更改事件
 * @param {Boolean} type值  true时获取文本值（优先判断）
* @param {Boolean} notBlur 是否触发值验证时间false时触发 
 */
/**
 * 获取表单的值
 * @function
 * @name Horn.Combox#getValue(value,type)
 *  @param {Boolean} value 值  false
 *   @param {Boolean} type 值  true时获取文本值（优先判断）
 * @return 表单的提交值
 */

/**
 * 获取由validate方法触发表单校验后的结果，并通过返回值标识校验的结果
 * @function
 * @name Horn.Combox#isValid
 * @return {Boolean} true表示校验通过，false表示校验失败
 */
/**
 * 触发校验表单的内容，然后通过调用isValid方法获取校验的结果
 * @function
 * @name Horn.Combox#validate
 */

Horn.Combox2 = Horn.extend(Horn.Combox,{
	COMPONENT_CLASS:"Combox2",
	pLabel : "",
	filterFlag : "",
	displayField : null,
	keyAttr : "label",
	pKeyAttr : "pkey",
	valueAttr : "value",
	defaultValue : "",
	defaultText : "",
	showLabel : true,
	/**
	 * @ignore
	 */
	init : function(dom) {
		Horn.Combox2.superclass.init.apply(this,arguments) ;
		this.combInit() ;
		//设置可能的多字典切换功能
		var _this3=this.textfieldObj;
		var _t=this;
		
			var keyAttr = this.el.attr('keyfield'),
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
		this.displayField = this.field;
		this.defaultValue = this.params.value || this.hidden.val();
//		this.setValue(this.defaultValue,true);
		//BUG #6838 combo_设置为只读的任然可以选择更改 设置为只读的任然可以选中后按退格键清除
		 if(this.params.check){
	        	this.checkRegx =[Horn.Validate.REQUIRED];
	       }
		if(this.params.readonly) {
        	this.setReadonly(true);
        }
		this.textfieldObj.bind({
            'focus' : function(){_t.validate();},
            'keydown':function(){_t.validate();},
            'keypress':function(){_t.validate();},
            'keyup':function(){_t.validate();}
       });
		//解决初始加载校验标红问题 
		if(this.params.check=="required"&&this.defaultValue!=null&&this.defaultValue!=""&&this.textfieldObj.val()==""){
			this.removeError();
		}
	},
    validate : function(){
    	var rs = true;
    	var _field = this;
        if(_field.disabled == true){
        	return true;
        }
    	if(!this.skipValid) {
    		//Horn.Validate.onValid({data:[Horn.Validate,_field]});
    		if(this.checkRegx.length>0){
			//if(_field.params.check && _field.params.check=="required"){
	    		var v = _field.textfieldObj.val();
	    		var _this2 =Horn.Validate;
	    		var obj2 = _field.textfieldObj;
	    		var rules = _this2.getRules.call(_this2, obj2);
	    		 if (rules && rules.length > 0 && obj2.attr("disabled")==undefined) {
	 		        _this2.isValide.call(_this2, rules, _field, v);
	 	        }
	 	        else{
	 	            _this2.removeError.call(_this2, _field);
	 	        }
	    	}  
	    }
    	
    	
    },
    showError : function(errorMsg){
    	var field = this.field; 
    	field.removeClass('m-verify-success');
    	field.addClass('m-verify-error');
    	
    	errorMsg = $.type(errorMsg) == "boolean" ? "校验错误" : errorMsg;
    	if(!this.msgDiv){
    		this.msgDiv = $('<div class="m-verify-tip bottom" role="m-verify-tip" style="display: none;"></div>');
    		this.el.after(this.msgDiv);
    	}
        var msg = this.msgDiv;
        msg.html("<div class=\"verify-tip-arrow\"></div><div class=\"verify-tip-inner\">"+errorMsg+"</div>");
        msg.css("display", "block");
        this.err = true;
        this.textfieldObj.removeClass('m-verify-success');
        this.textfieldObj.addClass('m-verify-error');
        
        
//    	var field = this.field; 
//    	errorMsg = $.type(errorMsg) == "boolean" ? "校验错误" : errorMsg;
//    	if(!this.msgDiv){
//    		this.msgDiv = $('<span class="hc_verification" style="display:none;"></span>');
//    		this.el.after(this.msgDiv);
//    	}
//        var msg = this.msgDiv;
//        msg.html(errorMsg);
//        msg.css("display", "none");
//        field.addClass('hc_ver-bd');
//        field.hover(function(){
//        	if(msg) msg.css("display", "inline");
//        },function(){
//        	if(msg) msg.css("display", "none");
//        });
//        this.err = true;  
//        this.textfieldObj.addClass('uv_ver-bd');
//        this.textfieldObj.hover(function(){
//        	if(msg) msg.css("display", "inline");
//        },function(){
//        	if(msg) msg.css("display", "none");
//        	
//        });
       
    },
    /**
     * 删除错误信息
     * @function
     * @name Horn.Field#removeError
     * @ignore
     */
    removeError : function(){
//    	this.field.removeClass('hc_ver-bd');
//        this.err = false;
//     	var msg = this.msgDiv;
//     	if(msg) msg.remove();
//     	delete this.msgDiv ;     
//     	this.textfieldObj.removeClass('uv_ver-bd');
     	
     	this.field.removeClass('m-verify-error');
     	this.textfieldObj.removeClass('m-verify-error');
        var input = this.get();
        var check = input.attr(Horn.Validate.CHECK);
        if (check) {
        	if(this.isValid){
            	this.field.addClass('m-verify-success');
                this.textfieldObj.addClass('m-verify-success');
            }
        }
        this.err = false;
    	var msg = this.msgDiv;
    	if(msg) msg.remove();
    	delete this.msgDiv ;
    },
    
    /**
     * @description 设置select的值
     * @function
     * @name Horn.Select#setValue
     * @param {String} value
     * @param {Boolean} type
     * @param {Boolean} triggerChange 是否触发值更改事件
     */
    setValue : function(value,triggerChange,type, notBlur) {
    	
    	this.handerHeadItem();
        var hidden = this.hidden ;
        var oldVal = hidden.val();
        var field = this.field ;
        //BUG #6560 【combox】(继承)clearValue() 不会将组件回复到原始状态
        //if (value === undefined || value === null || value==="") {
        if(type){
        	if (value === undefined || value === null) {
        		this.textfieldObj.val("");
                return false;
            }else{
            	this.textfieldObj.val(value);
            }
        }else{
        	if (value === undefined || value === null) {
            	field.val("");
                hidden.val("");
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
        
        
        
    } ,
    listClick : function(e) {
//    	this.fromListClick = true;
        var _this = this ;
        var _li = $(e.currentTarget);
        var listEl = e.data.listEl;
        var value = {} ;
        if (_this.multipleline) {
            var arrVal = new Array();
            var arrValTitle = new Array();
            $("input[type='checkbox']:checked", listEl).each(function(index, dom) {
                var curVal = $(dom).parent().parent("li[key]").attr("key");
                var curValTitle = $(dom).parent().parent("li[key]").attr("title");
                if (curVal) {
                    arrVal.push(curVal);
                    arrValTitle.push(curValTitle);
                }
            });
            this.tempCheckedKeyArray=arrVal;
            //为select内容过长的时候显示tips
            this.field.attr("TITLE",arrValTitle.toString());
            
            value["key"] = arrVal.join(_this.delimiter) ;
            value["text"] = arrValTitle.join(_this.delimiter) ;
            if ($("input[type='checkbox']", _li).get(0).checked) {
                _li.addClass("h_cur");
            } else {
                _li.removeClass("h_cur");
            }
            e.stopPropagation();
            this.setValue(value,true,false,true) ;
        } else {
            _li.addClass("h_cur");
            _li.siblings().removeClass("h_cur");
            this.setValue(_li.attr("key"),true,false,true) ;
        }
        
        //window下blur事件多次触发的问题
        this.field.trigger('blur');
        
      //将选中的值保存起来
      this.tempCheckedValue = this.field.val();
    } ,
    /**
     * 获取选中的值
     * @function
     * @name Horn.Select#getValue
     *  @param {String} type
     * @return String 返回选中的值
     */
    getValue : function(returnFieldValue,type){
    	this.handerHeadItem();
    	if(type){
    		   if(this.textfieldObj===null||this.textfieldObj=== undefined){
    			   return ""; 
    		   }
    		   else{
    			   return this.textfieldObj.val(); 
    		   }
              
    	}else{
    		if(returnFieldValue){
        		var _select = this;
        		var val = this.hidden.val() ;
        		var getVal = function(key){
        			return  _select.DICT[key] || null;
        		};
        		if(this.multipleline){
        			var valArr = val.split(this.delimiter);
        			var rtnVal = [];
        			$(valArr).each(function(i,item){
        				var transVal =getVal(item);
        				if(transVal){
        					rtnVal.push(transVal);
        				}
        			});
        			return rtnVal.join(this.delimiter);
        		}else{
        			return getVal(val);
        		}
        	}
    		return this.hidden.val() ;
    	}
        
    },
    /**
     * 获取提交的jQuery包装的field
     * @function
     * @name Horn.Field#get
     * @returns jQuery
     * @ignore
     */
    
    get : function(){
    	//新增text
    	 if(this.textfieldObj && this.textfieldObj.length){
             return this.textfieldObj;
         }
    	 //原来的
        if(this.hidden && this.hidden.length){
            return this.hidden ;
        }
        return this.field ;
    },
    /*
    addRule : function(rule) {
        var input = this.get();
        var check = input.attr(Horn.Validate.CHECK);
        if (check) {
            if (check.indexOf(rule) > -1) {
                return;
            }
            check += Horn.Validate.CHECKSEP + rule;
        } else {
            check = rule;
        }
        input.attr(Horn.Validate.CHECK, check);
        if(rule && rule.indexOf(Horn.Validate.REQUIRED) > -1){
            var li = this.el.parent();
            var span = $("span", li);
            var red = $("b.hc_red", span);
            if (!red.length) {
                red = $("<b>", {
                    "class" : "hc_red",
                    "html" : "*"
                });
                span.prepend(red);
            } else {
                red.html("*");
            }
            
        }
        this.removeError();
    },
    removeRule : function(rule) {
        var input = this.get();
        var check = input.attr(Horn.Validate.CHECK);
          //BUG #6518 【calendar】先进行非空校验的错误提示，然后调用removeRule("qq")，会造成非空校验的错误提示消失
        if (check && check.indexOf(rule) > -1) {//如果要去除的在原来的验证规则了就删除，否则不删除
            var checks = check.split(Horn.Validate.CHECKSEP);
            checks = $.grep(checks, function(c, index) {
                return c && c != rule;
            });
            input.attr(Horn.Validate.CHECK, checks.join(';'));
            this.removeError();
            this.setNotRequired();
        }
    },
    setRequired : function(required) {
    	if (required === false) {
    		this.setNotRequired();
    		return;
    	}
        this.addRule(Horn.Validate.REQUIRED);
    },
    setNotRequired : function() {
        var li = this.el.parent();
        var span = $("span", li);
        var red = $("b.hc_red", span);
        this.removeRule( Horn.Validate.REQUIRED);
        red.html("");
    },
    */
    /**
     * 设置是否只读，只读为true时，不可编辑不可下拉
     * @function
     * @name Horn.Field#setReadonly
     * @param {Boolean} readonly 是否只读
     * @ignore
     */
    setReadonly : function(readonly) {
    	if(!readonly) readonly=false;
    	var textId = this.params["textId"];  
		this.textfieldObj = this.el.find("#"+textId);
    	if (readonly === false) {
    		this.el.removeAttr("readonly");
    		this.textfieldObj.removeAttr("readonly");
    		this.readonly = false;
    	} else {
    		this.el.attr("readonly", true);
    		this.textfieldObj.attr("readonly", "readonly");
    		this.readonly = true;
    		this.setEditable(false);
    	}
    }
}) ;
Horn.Field.regFieldType("div.uv_combox",Horn.Combox2);