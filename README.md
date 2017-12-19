# MVPDemo

	MVP优化实现过程

	1.完成一个简单的mvp示例,/model包下的Model_1、/presenter包下的Presenter_1、/ui/view包下的View_1
		
	开始优化	
	2.解决mvp可能造成的泄漏：在Presenter进行绑定和分离attachView、detachView
	
	3.attachView、detachView为公共部分，为了缩减代码可抽象出来，定义一个BasePresenter

	4.把BasePresenter抽象出来之后发现View还是只能是特定的View_1，这里决定使用泛型。
	
	5.以上步骤已经基本完成P层优化，在Activity每次都要去调用P层中View的绑定和分离，因此自然而然的使用BaseActivity抽象类来完成优化（这里也使用泛型来完成）