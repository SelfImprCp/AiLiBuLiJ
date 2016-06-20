package com.anyin.ailibuli.base;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import android.widget.ListView;

import com.anyin.ailibuli.R;
import com.anyin.ailibuli.adapter.ListBaseAdapter;
import com.anyin.ailibuli.app.AppConfig;
import com.anyin.ailibuli.app.AppContext;
import com.anyin.ailibuli.bean.Entity;
import com.anyin.ailibuli.custom.EmptyLayout;
import com.anyin.ailibuli.custom.NoIntentView;
import com.anyin.ailibuli.event.IntentChangeEvent;
import com.anyin.ailibuli.utils.DateTimeUtil;
import com.anyin.ailibuli.utils.InternetUtil;
import com.anyin.ailibuli.utils.LogCp;
import com.anyin.ailibuli.utils.MyCache;
import com.anyin.ailibuli.utils.StringUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


@SuppressLint("NewApi")
public abstract class BaseListFragment<T extends Entity> extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener,
        AbsListView.OnScrollListener {

    public static final String BUNDLE_KEY_CATALOG = "BUNDLE_KEY_CATALOG";

    public SwipeRefreshLayout mSwipeRefreshLayout;

    public ListView mListView;

    protected ListBaseAdapter<T> mAdapter;

    public EmptyLayout mErrorLayout;

    protected int mStoreEmptyState = -1;

    protected int mCurrentPage = 1;

    protected int mCatalog = 1000;


    private AsyncTask<String, Void, List<T>> mCacheTask;
    private ParserTask mParserTask;


    // 没有网络
    // public NoIntentView listview_view_no_intent;

    // 总界面的布局
    public View view;

    // 暴露listview

    public ListView getMyListView(int type) {
        return mListView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_listview;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mCatalog = args.getInt(BUNDLE_KEY_CATALOG, 0);
        }
    }

    /**
     *
     */
    public void setHeadView() {
        /*
         * list_head_view = view; list_head_view.setVisibility(View.VISIBLE);
		 */
    }

    @Override
    public void initView(View view) {


        mSwipeRefreshLayout = (SwipeRefreshLayout) view
                .findViewById(R.id.swiperefreshlayout);
        mListView = (ListView) view.findViewById(R.id.listview);

        mErrorLayout = (EmptyLayout) view.findViewById(R.id.error_layout);

        LogCp.i(LogCp.CP, BaseListFragment.class + " 设置刷新？---》");


        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.setColorSchemeColors(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);

        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurrentPage = 1;
                mState = STATE_REFRESH;
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                requestData(true);
            }
        });

        mListView.setOnItemClickListener(this);
        mListView.setOnScrollListener(this);
        if (null != initHeaderView())
            mListView.addHeaderView(initHeaderView());

        if (mAdapter != null) {
            mListView.setAdapter(mAdapter);
            mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        } else {
            mAdapter = getListAdapter();
            mListView.setAdapter(mAdapter);

            if (requestDataIfViewCreated()) {
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
                mState = STATE_NONE;
                requestData(false);
            } else {
                mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
            }

        }
        if (mStoreEmptyState != -1) {
            mErrorLayout.setErrorType(mStoreEmptyState);
        }

        // 没有网络

        // listview_view_no_intent = (NoIntentView) view
        //       .findViewById(R.id.listview_view_no_intent);

        noIntentView();

    }

    @Override
    public void onDestroyView() {
        mStoreEmptyState = mErrorLayout.getErrorState();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        cancelReadCacheTask();
        cancelParserTask();

        super.onDestroy();
    }


    protected abstract ListBaseAdapter<T> getListAdapter();

    protected View initHeaderView() {
        // TODO Auto-generated method stub
        return null;
    }

    // 下拉刷新数据


    @Override
    public void onRefresh() {
        LogCp.i(LogCp.CP, BaseListFragment.class + "这是下拉刷新数据吗  ？？---》");


        if (mState == STATE_REFRESH) {
            return;
        }
        // 设置顶部正在刷新
        mListView.setSelection(0);
        setSwipeRefreshLoadingState();
        mCurrentPage = 1;
        mState = STATE_REFRESH;
        requestData(false);
    }


    protected boolean requestDataIfViewCreated() {
        return true;
    }

    protected String getCacheKeyPrefix() {
        return null;
    }

    protected List<T> parseList(String is) throws Exception {
        return null;
    }

    protected List<T> readList(Serializable seri) {
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
    }

    private String getCacheKey() {

		/*
         * LogCp.i(LogCp.CP, BaseListFragment.class + "getCacheKeyPrefix() " +
		 * getCacheKeyPrefix());
		 */
        return new StringBuilder(getCacheKeyPrefix()).append("_")
                .append(mCurrentPage).toString();

    }

    // 是否需要自动刷新
    protected boolean needAutoRefresh() {
        return true;
    }

    /***
     * 获取列表数据
     *
     * @param refresh 传放 false 不用缓存
     * @return void
     * @author 火蚁 2015-2-9 下午3:16:12
     */
    protected void requestData(boolean refresh) {

        // LogCp.i(LogCp.CP, BaseListFragment.class + "是否读缓存 中的数据  :" +
        // isReadCacheData(refresh));

        // 需要取缓存
        if (refresh) {

            LogCp.i(LogCp.CP, BaseListFragment.class + "是否读缓存 中的数据 requestData " + getCacheKey());

            // 读取到的json
            String cacheJson = (String) MyCache.getMyCache(getActivity())
                    .readString(getCacheKey());
            LogCp.i(LogCp.CP, BaseListFragment.class + "  缓存 中的数据 requestData " + cacheJson);

            try {
                // 如果json 不同空
                if (!StringUtils.isEmpty(cacheJson)) {
                    parseList(cacheJson);
                } else {
                    getDataServer();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            getDataServer();
        }

    }

    /**
     * 从服务器取数据
     */
    private void getDataServer() {
        if (InternetUtil.hasInternet()) {
            /**
             if (listview_view_no_intent != null) {
             listview_view_no_intent.setVisibility(View.GONE);
             }
             */

            if (null != mListView) {
                mListView.setVisibility(view.VISIBLE);
            }

            // 重新加载数据
            sendRequestData();
        } else {
            /**
             if (listview_view_no_intent != null) {
             listview_view_no_intent.setVisibility(View.VISIBLE);
             }
             */

            if (null != mListView) {
                mListView.setVisibility(view.GONE);
            }
            if (null != mErrorLayout) {

                mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
                mErrorLayout
                        .setErrorMessage(getString(R.string.tip_network_error));
            }

        }
    }

    /***
     * 判断是否需要读取缓存的数据
     *
     * @author 火蚁 2015-2-10 下午2:41:02
     *
     * @return boolean
     * @param refresh
     * @return
     */
    // private boolean isReadCacheData(boolean refresh) {
    // String key = getCacheKey();
    // /*
    // * LogCp.i(LogCp.CP, BaseListFragment.class + "缓存中文件保存的路径:" + key);
    // */
    // if (!InternetUtil.hasInternetConnected()) {
    // return true;
    // }
    // // 第一页若不是主动刷新，缓存存在，优先取缓存的
    // if (CacheManager.isExistDataCache(getActivity(), key) && !refresh &&
    // mCurrentPage == 1) {
    // return true;
    // }
    // // 其他页数的，缓存存在以及还没有失效，优先取缓存的
    // if (CacheManager.isExistDataCache(getActivity(), key) &&
    // !CacheManager.isCacheDataFailure(getActivity(), key)
    // && mCurrentPage != 1) {
    // return true;
    // }
    //
    // return false;
    // }

    // 是否到时间去刷新数据了
     /*
    private boolean onTimeRefresh() {
     String lastRefreshTime = AppContext.getLastRefreshTime(getCacheKey());
     String currTime = DateTimeUtil.getCurTimeStr();
    long diff = DateTimeUtil.calDateDifferent(lastRefreshTime, currTime);
      return needAutoRefresh() && diff > getAutoRefreshTime();
     }
 */

    /***
     * 自动刷新的时间
     * <p>
     * 默认：自动刷新的时间为半天时间
     *
     * @return
     * @author 火蚁 2015-2-9 下午5:55:11
     */
    protected long getAutoRefreshTime() {
        return 12 * 60 * 60;

    }

    @Override
    public void onResume() {
        super.onResume();
        //  if (onTimeRefresh()) {
        //onRefresh();
        // }
    }

    protected void sendRequestData() {
    }

    private void readCacheData(String cacheKey) {
        // cancelReadCacheTask();

        LogCp.i(LogCp.CP, BaseListFragment.class + "读取缓存 文件中的路径:" + cacheKey);

        // mCacheTask = new CacheTask(getActivity()).execute(cacheKey);
    }

    private void cancelReadCacheTask() {
        if (mCacheTask != null) {
            mCacheTask.cancel(true);
            mCacheTask = null;
        }
    }

    // private class CacheTask extends AsyncTask<String, Void, List<T>> {
    // private final WeakReference<Context> mContext;
    //
    // private CacheTask(Context context) {
    // mContext = new WeakReference<Context>(context);
    // }
    //
    // @Override
    // protected List<T> doInBackground(String... params) {
    // Serializable seri = CacheManager.readObject(mContext.get(), params[0]);
    // if (seri == null) {
    // return null;
    // } else {
    // return readList(seri);
    // }
    // }
    //
    // protected void onPostExecute(List<T> list) {
    // super.onPostExecute(list);
    //
    // list = null;
    //
    // if (list != null) {
    // LogCp.i(LogCp.CP, BaseListFragment.class + "首缓存中的数据----》 " +
    // list.size());
    //
    // executeOnLoadDataSuccess(list);
    // } else {
    //
    // // 没有数据 读取缓存没有数据 ，
    //
    // // 取新的数据
    // sendRequestData();
    //
    // // UIHelper.showToast("暂时没有数据哦");
    // // executeOnLoadDataError(null);
    // // executeOnLoadDataError();
    // }
    // executeOnLoadFinish();
    // }
    // }

    // private class SaveCacheTask extends AsyncTask<Void, Void, Void> {
    // private final WeakReference<Context> mContext;
    // private final List seri;
    // private final String key;
    //
    // private SaveCacheTask(Context context, List seri, String key) {
    // mContext = new WeakReference<Context>(context);
    // this.seri = seri;
    // this.key = key;
    // }
    //
    // @Override
    // protected Void doInBackground(Void... params) {
    // CacheManager.saveObject(mContext.get(), (Serializable) seri, key);
    // return null;
    // }
    // }

    protected AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            String arg1 = StringUtils.byteToString(responseBody);
            LogCp.i(LogCp.CP, BaseListFragment.class + " ===========请求回的数据 ===========，，" + arg1);
            if (mCurrentPage == 1 && needAutoRefresh()) {
                // AppContext.putToLastRefreshTime(getCacheKey(),
                //       DateTimeUtil.getCurTimeStr());
            }
            if (isAdded()) {
                if (mState == STATE_REFRESH) {
                    onRefreshNetworkSuccess();
                }
                executeParserTask(arg1);
            }
            LogCp.i(LogCp.CP, BaseListFragment.class + "ave cache ，" + getCacheKey() + arg1);

            // 缓存json数据
            MyCache.getMyCache(getActivity()).saveString(getCacheKey(), arg1);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            error.printStackTrace();
        }


    };

    protected void executeOnLoadDataSuccess(List<T> data) {
        if (data == null) {
            data = new ArrayList<T>();
        }
		/*
		 * LogCp.i(LogCp.CP, BaseListFragment.class + "执行，" + "调用了吗？ " +
		 * data.size());
		 */
        // 判断数据操作是否成功

		/*
		 * if (mResult != null && !mResult.OK()) {
		 * AppContext.showToast(mResult.getErrorMessage()); //
		 * 注销登陆，密码已经修改，cookie，失效了 AppContext.getInstance().Logout(); }
		 */

        mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
        if (mCurrentPage == 1) {
            mAdapter.clear();
        }

        LogCp.i(LogCp.CP,
                BaseListFragment.class + "比较前有多少数据 ，， ，，" + data.size());

        for (int i = 0; i < data.size(); i++) {
            if (compareTo(mAdapter.getData(), data.get(i))) {
                data.remove(i);
                i--;
            }
        }

        LogCp.i(LogCp.CP, BaseListFragment.class + "比较后有多少数据， ，，" + data.size());

        LogCp.i(LogCp.CP, BaseListFragment.class
                + " mAdapter.getCount() + data.size()，，" + mAdapter.getCount()
                + data.size());

        int adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
        if ((mAdapter.getCount() + data.size()) == 0) {
            adapterState = ListBaseAdapter.STATE_EMPTY_ITEM;
        } else if (data.size() == 0
                || (data.size() < getPageSize() && mCurrentPage == 1)) {
            adapterState = ListBaseAdapter.STATE_NO_MORE;
            mAdapter.notifyDataSetChanged();
        } else {
            adapterState = ListBaseAdapter.STATE_LOAD_MORE;
        }
        mAdapter.setState(adapterState);
        mAdapter.addData(data);
        // 判断等于是因为最后有一项是listview的状态
        if (mAdapter.getCount() == 1) {

            if (needShowEmptyNoData()) {
                mErrorLayout.setErrorType(EmptyLayout.NODATA);
            } else {
                mAdapter.setState(ListBaseAdapter.STATE_EMPTY_ITEM);
                mAdapter.notifyDataSetChanged();
            }
        }

    }

    /**
     * 是否需要隐藏listview，显示无数据状态
     *
     * @author 火蚁 2015-1-27 下午6:18:59
     */
    protected boolean needShowEmptyNoData() {
        return true;
    }

    protected boolean compareTo(List<? extends Entity> data, Entity enity) {
        int s = data.size();
        if (enity != null) {
            for (int i = 0; i < s; i++) {
                if (enity.getId() == data.get(i).getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected int getPageSize() {
        return AppConfig.PAGE_SIZE;
    }

    protected void onRefreshNetworkSuccess() {
    }

    protected void executeOnLoadDataError(String error) {
        /**
         if (mCurrentPage == 1
         && !CacheManager.isExistDataCache(getActivity(), getCacheKey())) {
         mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
         } else {
         mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
         mAdapter.setState(ListBaseAdapter.STATE_NETWORK_ERROR);
         mAdapter.notifyDataSetChanged();
         }
         */
    }

    // 完成刷新
    protected void executeOnLoadFinish() {
        setSwipeRefreshLoadedState();
        mState = STATE_NONE;
    }

    /**
     * 设置顶部正在加载的状态
     */
    private void setSwipeRefreshLoadingState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
            // 防止多次重复刷新
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    /**
     * 设置顶部加载完毕的状态
     */
    private void setSwipeRefreshLoadedState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    private void executeParserTask(String data) {
        cancelParserTask();
        mParserTask = new ParserTask(data);
        mParserTask.execute();
    }

    private void cancelParserTask() {
        if (mParserTask != null) {
            mParserTask.cancel(true);
            mParserTask = null;
        }
    }

    class ParserTask extends AsyncTask<Void, Void, String> {

        private final String reponseData;
        private boolean parserError;
        private List<T> list;

        public ParserTask(String data) {
            this.reponseData = data;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                list = parseList(reponseData);
                LogCp.i(LogCp.CP, BaseListFragment.class + "解析 出来的数据 的，值 ，，"
                        + list);

                // new SaveCacheTask(getActivity(), list,
                // getCacheKey()).execute();

				/*
				 * if (list == null) { Response res =
				 * GsonUtil.jsonStrToBean(reponseData, Response.class); if (res
				 * != null) { mResult = resultBean.getResult(); }
				 *
				 * }
				 */
            } catch (Exception e) {
                e.printStackTrace();

                parserError = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (parserError) {
                readCacheData(getCacheKey());
            } else {
                executeOnLoadDataSuccess(list);
                executeOnLoadFinish();
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mAdapter == null || mAdapter.getCount() == 0) {
            return;
        }
        // 数据已经全部加载，或数据为空时，或正在加载，不处理滚动事件
        if (mState == STATE_LOADMORE || mState == STATE_REFRESH) {
            return;
        }
        // 判断是否滚动到底部
        boolean scrollEnd = false;
        try {
            if (view.getPositionForView(mAdapter.getFooterView()) == view
                    .getLastVisiblePosition())
                scrollEnd = true;
        } catch (Exception e) {
            scrollEnd = false;
        }

        if (mState == STATE_NONE && scrollEnd) {
            if (mAdapter.getState() == ListBaseAdapter.STATE_LOAD_MORE
                    || mAdapter.getState() == ListBaseAdapter.STATE_NETWORK_ERROR) {
                mCurrentPage++;
                mState = STATE_LOADMORE;
                requestData(false);
                mAdapter.setFooterViewLoading();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // 数据已经全部加载，或数据为空时，或正在加载，不处理滚动事件
        // if (mState == STATE_NOMORE || mState == STATE_LOADMORE
        // || mState == STATE_REFRESH) {
        // return;
        // }
        // if (mAdapter != null
        // && mAdapter.getDataSize() > 0
        // && mListView.getLastVisiblePosition() == (mListView.getCount() - 1))
        // {
        // if (mState == STATE_NONE
        // && mAdapter.getState() == BaseAdapterList.STATE_LOAD_MORE) {
        // mState = STATE_LOADMORE;
        // mCurrentPage++;
        // requestData(true);
        // }
        // }
    }


    /**
     * 网络 改变的事件
     *
     * @param event
     */
    public void onEvent(IntentChangeEvent event) {
        getDataServer();

    }

    ;

    /**
     *
     */
    private void noIntentView() {

        /**
         if (InternetUtil.hasInternet()) {
         listview_view_no_intent.setVisibility(View.GONE);
         mListView.setVisibility(view.VISIBLE);

         } else {

         listview_view_no_intent.setVisibility(View.VISIBLE);
         mListView.setVisibility(view.GONE);

         }


         */
    }
}
