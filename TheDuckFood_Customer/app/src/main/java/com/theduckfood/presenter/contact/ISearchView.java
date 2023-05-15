package com.theduckfood.presenter.contact;

import com.theduckfood.model.respone.SearchResponse;

public interface ISearchView {
    void searchResponse(SearchResponse searchResponse);
    void loading(boolean isLoading);
}
